package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Nivel;
import br.com.fiap.biblioteca.repositorio.RepositorioNivel;

public class NivelService {

    private RepositorioNivel repositorioNivel;

    public NivelService(RepositorioNivel repositorioNivel) {
        this.repositorioNivel = repositorioNivel;
    }

    public void adicionar(Nivel nivel) {
        repositorioNivel.salvar(nivel);
    }

    public Nivel buscarPorId(int id) {
        return repositorioNivel.buscarPorId(id);
    }

    public Nivel buscarPorNome(String nome) {
        return repositorioNivel.buscarPorNome(nome);
    }

    public void excluir(int id) {
        repositorioNivel.excluir(id);
    }
}
