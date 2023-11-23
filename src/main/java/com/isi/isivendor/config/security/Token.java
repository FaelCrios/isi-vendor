package com.isi.isivendor.config.security;

import java.util.Date;
import java.util.Objects;

public class Token {

    private String usuario;
    private Boolean authenticado;

    private Date criado;

    private Date expirado;

    private String tokenAcesso;

    private String tokenRefresh;

    public Token(String usuario, Boolean authenticado, Date criado, Date expirado, String tokenAcesso, String tokenRefresh) {
        this.usuario = usuario;
        this.authenticado = authenticado;
        this.criado = criado;
        this.expirado = expirado;
        this.tokenAcesso = tokenAcesso;
        this.tokenRefresh = tokenRefresh;
    }

    public Token() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getAuthenticado() {
        return authenticado;
    }

    public void setAuthenticado(Boolean authenticado) {
        this.authenticado = authenticado;
    }

    public Date getCriado() {
        return criado;
    }

    public void setCriado(Date criado) {
        this.criado = criado;
    }

    public Date getExpirado() {
        return expirado;
    }

    public void setExpirado(Date expirado) {
        this.expirado = expirado;
    }

    public String getTokenAcesso() {
        return tokenAcesso;
    }

    public void setTokenAcesso(String tokenAcesso) {
        this.tokenAcesso = tokenAcesso;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(usuario, token.usuario) && Objects.equals(authenticado, token.authenticado) && Objects.equals(criado, token.criado) && Objects.equals(expirado, token.expirado) && Objects.equals(tokenAcesso, token.tokenAcesso) && Objects.equals(tokenRefresh, token.tokenRefresh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, authenticado, criado, expirado, tokenAcesso, tokenRefresh);
    }
}
