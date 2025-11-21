package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Nivel;
import br.com.fiap.biblioteca.repositorio.RepositorioNivel;

import java.util.ArrayList;

public class NivelDAO implements RepositorioNivel {

    private static final ArrayList<Nivel> niveis = new ArrayList<>();

    @Override
    public void salvar(Nivel nivel) {
        niveis.add(nivel);
    }

    @Override
    public Nivel buscarPorId(int idNivel) {
        return niveis.stream()
                .filter(n -> n.getId_nivel() == idNivel)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Nivel buscarPorNome(String nomeNivel) {
        return niveis.stream()
                .filter(n -> n.getNome_nivel().equalsIgnoreCase(nomeNivel))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Nivel> listarTodos() {
        return new ArrayList<>(niveis);
    }

    @Override
    public void excluir(int idNivel) {
        niveis.removeIf(n -> n.getId_nivel() == idNivel);
    }
}
