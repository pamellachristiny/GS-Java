package br.com.fiap.biblioteca.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NivelUsuario {

    @JsonProperty
    private int id_usuario;

    @JsonProperty
    private int id_nivel;

    @JsonProperty
    private Date data_hora_nivel;

    public NivelUsuario() {}

    public NivelUsuario(int id_usuario, int id_nivel, Date data_hora_nivel) {
        this.id_usuario = id_usuario;
        this.id_nivel = id_nivel;
        this.data_hora_nivel = data_hora_nivel;
    }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public int getId_nivel() { return id_nivel; }
    public void setId_nivel(int id_nivel) { this.id_nivel = id_nivel; }

    public Date getData_hora_nivel() { return data_hora_nivel; }
    public void setData_hora_nivel(Date data_hora_nivel) { this.data_hora_nivel = data_hora_nivel; }
}
