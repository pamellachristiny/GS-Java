package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Usuario;
import br.com.fiap.biblioteca.repositorio.RepositorioUsuario;

import java.util.ArrayList;

public class UsuarioService {

    private RepositorioUsuario repositorioUsuarios;

    public UsuarioService(RepositorioUsuario repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public void adicionar(Usuario usuario) {
        repositorioUsuarios.salvar(usuario);
    }

    public void atualizar(int id, Usuario usuario) {
        repositorioUsuarios.atualizar(id, usuario);
    }

    public Usuario buscarPorId(int id) {
        return repositorioUsuarios.buscarPorId(id);
    }

    public Usuario buscarPorEmail(String email) {
        return repositorioUsuarios.buscarPorEmail(email);
    }

    public ArrayList<Usuario> listarTodos() {
        return repositorioUsuarios.listarTodos();
    }

    public void excluir(int id) {
        repositorioUsuarios.excluir(id);
    }
}

