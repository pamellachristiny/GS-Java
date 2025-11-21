package br.com.fiap.biblioteca.dominio;

public class Curso {

    private Long id_curso;
    private String nomeCurso;
    private Long idProfessor;
    private Long idChallenge;

    public Curso() {
    }

    public Curso(Long id_curso, String nomeCurso, Long idProfessor, Long idChallenge) {
        this.id_curso = id_curso;
        this.nomeCurso = nomeCurso;
        this.idProfessor = idProfessor;
        this.idChallenge = idChallenge;
    }

    public Curso(String nomeCurso, Long idProfessor, Long idChallenge) {
        this.nomeCurso = nomeCurso;
        this.idProfessor = idProfessor;
        this.idChallenge = idChallenge;
    }

    public Long getIdCurso() {
        return id_curso;
    }

    public void setIdCurso(Long idCurso) {
        this.id_curso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Long getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(Long idChallenge) {
        this.idChallenge = idChallenge;
    }
}
