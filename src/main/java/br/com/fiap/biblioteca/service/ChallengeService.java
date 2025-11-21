package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Challenge;
import br.com.fiap.biblioteca.repositorio.RepositorioChallenge;

import java.util.List;

public class ChallengeService {

    private final RepositorioChallenge repositorio;

    public ChallengeService(RepositorioChallenge repositorio) {
        this.repositorio = repositorio;
    }

    public void salvar(Challenge challenge) {
        repositorio.salvar(challenge);
    }

    public Challenge buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public List<Challenge> listarTodos() {
        return repositorio.listarTodos();
    }

    public void excluir(Long id) {
        repositorio.excluir(id);
    }
}

