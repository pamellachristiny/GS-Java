package br.com.fiap.biblioteca.dominio;

import java.util.Date;

public class Conversa {

    private String id_conversa;
    private int id_usuario;
    private Date dataHora;
    private String conteudo;

    public Conversa() {}

    public Conversa(String idConversa, int idUsuario, Date dataHora, String conteudo) {
        this.id_conversa = idConversa;
        this.id_usuario = idUsuario;
        this.dataHora = dataHora;
        this.conteudo = conteudo;
    }

    public String getIdConversa() {
        return id_conversa;
    }

    public String getId() { // alias opcional
        return id_conversa;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getConteudo() {
        return conteudo;
    }
}

