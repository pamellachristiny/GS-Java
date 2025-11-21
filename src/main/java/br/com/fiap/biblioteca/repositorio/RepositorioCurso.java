package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Curso;

import java.util.ArrayList;

public interface RepositorioCurso {

    void salvar(Curso curso);

    ArrayList<Curso> buscarTodos();

    Curso buscarPorId(Long id);

    ArrayList<Curso> buscarPorProfessor(Long idProfessor);

    void atualizar(Curso curso);

    void excluir(Long id);
}
