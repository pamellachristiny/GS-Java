package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.NivelUsuario;
import br.com.fiap.biblioteca.repositorio.RepositorioNivelUsuario;

public class NivelUsuarioService {

    private RepositorioNivelUsuario repositorioNivelUsuario;

    public NivelUsuarioService(RepositorioNivelUsuario repositorioNivelUsuario) {
        this.repositorioNivelUsuario = repositorioNivelUsuario;
    }

    public void adicionar(NivelUsuario nivelUsuario) {
        repositorioNivelUsuario.salvar(nivelUsuario);
    }
}

