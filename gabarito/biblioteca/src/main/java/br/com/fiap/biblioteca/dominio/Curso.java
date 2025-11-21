package br.com.fiap.biblioteca.dominio;

public class Curso {

    private Long idCurso;
    private String nomeCurso;
    private Long idProfessor;
    private Long idChallenge;

    public Curso() {
    }

    public Curso(Long idCurso, String nomeCurso, Long idProfessor, Long idChallenge) {
        this.idCurso = idCurso;
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
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
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
