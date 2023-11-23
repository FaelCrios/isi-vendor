package com.isi.isivendor.config.security.securityJwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.isi.isivendor.config.security.Token;
import com.isi.isivendor.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMillieSeconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public Token criarTokenAcesso(String usuario, List<String> roles){
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + validityInMillieSeconds);
        var tokenAcesso = getTokenAcesso(usuario, roles, agora, validade);
        var tokenRefresh = getRefreshToken(usuario, roles, agora);
        return new Token(usuario, true, agora, validade, tokenAcesso, tokenRefresh);
    }

    private String getRefreshToken(String usuario, List<String> roles, Date agora) {
        Date validadeRefreshToken = new Date(agora.getTime() + (validityInMillieSeconds * 3));

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validadeRefreshToken)
                .withSubject(usuario)
                .sign(algorithm)
                .strip();
    }

    private String getTokenAcesso(String usuario, List<String> roles, Date agora, Date validade) {
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validade)
                .withSubject(usuario)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }

    public String respostaToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }


    public boolean validaToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try{
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        }catch (Exception e){
            throw new InvalidJwtAuthenticationException("Token JWT expirado");
        }
    }
}
