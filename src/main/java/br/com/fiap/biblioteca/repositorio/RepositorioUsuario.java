package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Usuario;
import java.util.ArrayList;

public interface RepositorioUsuario {

    void salvar(Usuario usuario);

    Usuario buscarPorId(int id_usuario);

    Usuario buscarPorEmail(String email);

    ArrayList<Usuario> listarTodos();

    void excluir(int id);

    void atualizar(int id, Usuario usuario);

}
