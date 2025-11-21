package br.com.fiap.biblioteca.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Professor {

    @JsonProperty
    private int id_professor;

    @JsonProperty
    private String nome_professor;

    @JsonProperty
    private String especialidade_professor;

    @JsonProperty
    private String email_professor;

    @JsonProperty
    private String senha_professor;

    public Professor() {
    }

    public Professor(int id_professor, String nome_professor, String especialidade_professor,
                     String email_professor, String senha_professor) {
        this.id_professor = id_professor;
        this.nome_professor = nome_professor;
        this.especialidade_professor = especialidade_professor;
        this.email_professor = email_professor;
        this.senha_professor = senha_professor;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getEspecialidade_professor() {
        return especialidade_professor;
    }

    public void setEspecialidade_professor(String especialidade_professor) {
        this.especialidade_professor = especialidade_professor;
    }

    public String getEmail_professor() {
        return email_professor;
    }

    public void setEmail_professor(String email_professor) {
        this.email_professor = email_professor;
    }

    public String getSenha_professor() {
        return senha_professor;
    }

    public void setSenha_professor(String senha_professor) {
        this.senha_professor = senha_professor;
    }
}
