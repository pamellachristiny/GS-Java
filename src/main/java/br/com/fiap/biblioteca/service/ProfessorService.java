package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Professor;
import br.com.fiap.biblioteca.repositorio.RepositorioProfessor;

import java.util.ArrayList;

public class ProfessorService {

    private RepositorioProfessor repositorioProfessores;

    public ProfessorService(RepositorioProfessor repositorioProfessores) {
        this.repositorioProfessores = repositorioProfessores;
    }

    public void adicionar(Professor professor) {
        repositorioProfessores.salvar(professor);
    }

    public Professor buscar(int id) {
        return repositorioProfessores.buscarPorId(id);
    }

    public Professor buscarPorEmail(String email) {
        return repositorioProfessores.buscarPorEmail(email);
    }

    public ArrayList<Professor> listar() {
        return repositorioProfessores.listarTodos();
    }

    public void atualizar(Professor professor) {
        repositorioProfessores.atualizar(professor);
    }

    public boolean excluir(int id) {
        Professor professor = repositorioProfessores.buscarPorId(id);

        if (professor == null) {
            return false;  // Não existe
        }

        repositorioProfessores.excluir(id);
        return true;       // Removido com sucesso
    }

}
