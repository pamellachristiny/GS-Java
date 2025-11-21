package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Curso;
import br.com.fiap.biblioteca.repositorio.RepositorioCurso;

public class CursoService {

    private RepositorioCurso repositorioCurso;

    public CursoService(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }

    public void adicionar(Curso curso) {
        repositorioCurso.salvar(curso);
    }

    public void atualizar(Curso curso) {
        repositorioCurso.atualizar(curso);
    }

    public void excluir(Long id) {
        repositorioCurso.excluir(id);
    }
}
