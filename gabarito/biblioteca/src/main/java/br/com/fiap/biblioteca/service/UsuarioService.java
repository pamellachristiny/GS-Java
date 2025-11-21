package br.com.fiap.biblioteca.service;
 import br.com.fiap.biblioteca.dominio.Usuario;
 import br.com.fiap.biblioteca.repositorio.RepositorioUsuario;
 public class UsuarioService {
     private RepositorioUsuario repositorioUsuarios;
     public UsuarioService(RepositorioUsuario repositorioUsuarios)
     { this.repositorioUsuarios = repositorioUsuarios; }
     public void adicionar(Usuario usuario) { repositorioUsuarios.salvar(usuario);
     }
 }