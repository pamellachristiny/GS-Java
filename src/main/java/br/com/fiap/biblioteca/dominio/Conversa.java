package br.com.fiap.biblioteca.dominio;

public class Conversa {

    private String idConversa;
    private int idUsuario;
    private String dataHora;
    private String conteudo;

    public Conversa() {}

    public Conversa(String idConversa, int idUsuario, String dataHora, String conteudo) {
        this.idConversa = idConversa;
        this.idUsuario = idUsuario;
        this.dataHora = dataHora;
        this.conteudo = conteudo;
    }

    public String getIdConversa() {
        return idConversa;
    }

    public String getId() { // alias opcional
        return idConversa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getConteudo() {
        return conteudo;
    }
}

