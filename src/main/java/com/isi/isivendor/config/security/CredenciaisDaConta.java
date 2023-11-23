package com.isi.isivendor.config.security;

import java.util.Objects;

public class CredenciaisDaConta {

    private String usuario;

    private String senha;

    public CredenciaisDaConta(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredenciaisDaConta that = (CredenciaisDaConta) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, senha);
    }
}
