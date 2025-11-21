package br.com.fiap.biblioteca.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversaIndicaChallenge {

    @JsonProperty
    private int id_conversa_indica_challenge;

    @JsonProperty
    private String id_conversa;

    @JsonProperty
    private int id_challenge;

    public ConversaIndicaChallenge() {}

    public ConversaIndicaChallenge(int id_conversa_indica_challenge, String id_conversa, int id_challenge) {
        this.id_conversa_indica_challenge = id_conversa_indica_challenge;
        this.id_conversa = id_conversa;
        this.id_challenge = id_challenge;
    }

    public int getId_conversa_indica_challenge() { return id_conversa_indica_challenge; }
    public void setId_conversa_indica_challenge(int id_conversa_indica_challenge) { this.id_conversa_indica_challenge = id_conversa_indica_challenge; }

    public String getId_conversa() { return id_conversa; }
    public void setId_conversa(String id_conversa) { this.id_conversa = id_conversa; }

    public int getId_challenge() { return id_challenge; }
    public void setId_challenge(int id_challenge) { this.id_challenge = id_challenge; }
}
