package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Professor;
import br.com.fiap.biblioteca.repositorio.RepositorioProfessor;

public class ProfessorService {

    private RepositorioProfessor repositorioProfessores;

    public ProfessorService(RepositorioProfessor repositorioProfessores) {
        this.repositorioProfessores = repositorioProfessores;
    }

    public void adicionar(Professor professor) {
        repositorioProfessores.salvar(professor);
    }
}
