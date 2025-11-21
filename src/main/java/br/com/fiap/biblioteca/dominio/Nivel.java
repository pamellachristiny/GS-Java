package br.com.fiap.biblioteca.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Nivel {

    @JsonProperty
    private int id_nivel;

    @JsonProperty
    private String nome_nivel;

    @JsonProperty
    private String descricao_nivel;

    public Nivel() {}

    public Nivel(int id_nivel, String nome_nivel, String descricao_nivel) {
        this.id_nivel = id_nivel;
        this.nome_nivel = nome_nivel;
        this.descricao_nivel = descricao_nivel;
    }

    public int getId_nivel() { return id_nivel; }
    public void setId_nivel(int id_nivel) { this.id_nivel = id_nivel; }

    public String getNome_nivel() { return nome_nivel; }
    public void setNome_nivel(String nome_nivel) { this.nome_nivel = nome_nivel; }

    public String getDescricao_nivel() { return descricao_nivel; }
    public void setDescricao_nivel(String descricao_nivel) { this.descricao_nivel = descricao_nivel; }
}
