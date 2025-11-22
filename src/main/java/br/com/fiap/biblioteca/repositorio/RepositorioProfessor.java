package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Professor;
import java.util.ArrayList;

public interface RepositorioProfessor {

    void salvar(Professor professor);

    Professor buscarPorId(int idProfessor);

    Professor buscarPorEmail(String email);

    ArrayList<Professor> listarTodos();

    void atualizar(Professor professor);

    void excluir(int id);
}
