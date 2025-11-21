package br.com.fiap.biblioteca.dominio;

public class Challenge {

    private Long id_challenge;
    private Long id_usuario;
    private Long id_curso;
    private String nome_challenge;
    private String descricao_challenge;

    // Construtor COMPLETO (para SELECT no DAO)
    public Challenge(Long id_challenge, Long id_usuario, Long id_curso,
                     String nome_challenge, String descricao_challenge) {
        this.id_challenge = id_challenge;
        this.id_usuario = id_usuario;
        this.id_curso = id_curso;
        this.nome_challenge = nome_challenge;
        this.descricao_challenge = descricao_challenge;
    }

    // Construtor SEM ID (para INSERT)
    public Challenge(Long id_usuario, Long id_curso,
                     String nome_challenge, String descricao_challenge) {
        this.id_usuario = id_usuario;
        this.id_curso = id_curso;
        this.nome_challenge = nome_challenge;
        this.descricao_challenge = descricao_challenge;
    }

    public Long getId_challenge() {
        return id_challenge;
    }

    public void setId_challenge(Long id_challenge) {
        this.id_challenge = id_challenge;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    public String getNome_challenge() {
        return nome_challenge;
    }

    public void setNome_challenge(String nome_challenge) {
        this.nome_challenge = nome_challenge;
    }

    public String getDescricao_challenge() {
        return descricao_challenge;
    }

    public void setDescricao_challenge(String descricao_challenge) {
        this.descricao_challenge = descricao_challenge;
    }
}
