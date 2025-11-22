package br.com.fiap.biblioteca.dominio;

public class Curso {

    private Long id_curso;
    private String nome_curso;
    private Long id_professor;
    private Long id_challenge;

    public Curso() {
    }

    public Curso(Long id_curso, String nomeCurso, Long idProfessor, Long idChallenge) {
        this.id_curso = id_curso;
        this.nome_curso = nomeCurso;
        this.id_professor = idProfessor;
        this.id_challenge = idChallenge;
    }

    public Curso(String nomeCurso, Long idProfessor, Long idChallenge) {
        this.nome_curso = nomeCurso;
        this.id_professor = idProfessor;
        this.id_challenge = idChallenge;
    }

    public Long getIdCurso() {
        return id_curso;
    }

    public void setIdCurso(Long idCurso) {
        this.id_curso = idCurso;
    }

    public String getNomeCurso() {
        return nome_curso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nome_curso = nomeCurso;
    }

    public Long getIdProfessor() {
        return id_professor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.id_professor = idProfessor;
    }

    public Long getIdChallenge() {
        return id_challenge;
    }

    public void setIdChallenge(Long idChallenge) {
        this.id_challenge = idChallenge;
    }
}
