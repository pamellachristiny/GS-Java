package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.ConversaIndicaChallenge;
import br.com.fiap.biblioteca.repositorio.RepositorioConversaIndicaChallenge;

public class ConversaIndicaChallengeService {

    private RepositorioConversaIndicaChallenge repositorio;

    public ConversaIndicaChallengeService(RepositorioConversaIndicaChallenge repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(ConversaIndicaChallenge conversaIndicaChallenge) {
        repositorio.salvar(conversaIndicaChallenge);
    }
}
