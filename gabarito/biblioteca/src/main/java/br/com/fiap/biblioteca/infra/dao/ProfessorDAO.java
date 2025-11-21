package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Professor;
import br.com.fiap.biblioteca.repositorio.RepositorioProfessor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements RepositorioProfessor {

    // Simulação de banco de dados em memória
    private static final List<Professor> professores = new ArrayList<>();

    @Override
    public void salvar(Professor professor) {
        professores.add(professor);
    }

    @Override
    public Professor buscarPorId(int idProfessor) {
        return professores.stream()
                .filter(p -> p.getId_professor() == idProfessor)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Professor buscarPorEmail(String email) {
        return professores.stream()
                .filter(p -> p.getEmail_professor().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Professor> listarTodos() {
        return new ArrayList<>(professores);
    }

    @Override
    public void excluir(int id) {
        professores.removeIf(p -> p.getId_professor() == id);
    }
}
