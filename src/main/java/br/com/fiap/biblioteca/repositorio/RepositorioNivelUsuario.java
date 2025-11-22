package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.NivelUsuario;
import java.util.ArrayList;

public interface RepositorioNivelUsuario {

    void salvar(NivelUsuario nivelUsuario);

    NivelUsuario buscar(int id_usuario, int id_nivel);

    ArrayList<NivelUsuario> buscarPorUsuario(int id_usuario);

    ArrayList<NivelUsuario> listarTodos();

    void excluir(int id_usuario, int id_nivel);

    void atualizar(NivelUsuario nivelUsuario);
}
