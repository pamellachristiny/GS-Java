package br.com.fiap.biblioteca.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    @JsonProperty
    private int id_usuario;

    @JsonProperty
    private String nome_usuario;

    @JsonProperty
    private String email_usuario;

    @JsonProperty
    private String senha_usuario;

    @JsonProperty
    private String plano_usuario;

    public Usuario() {
    }

    public Usuario(int id_usuario,
                   String nome_usuario,
                   String email_usuario,
                   String senha_usuario,
                   String plano_usuario) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
        this.plano_usuario = plano_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getPlano_usuario() {
        return plano_usuario;
    }

    public void setPlano_usuario(String plano_usuario) {
        this.plano_usuario = plano_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nome_usuario='" + nome_usuario + '\'' +
                ", email_usuario='" + email_usuario + '\'' +
                ", plano_usuario='" + plano_usuario + '\'' +
                '}';
    }
}
