package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.NivelUsuario;
import java.util.ArrayList;

public interface RepositorioNivelUsuario {

    void salvar(NivelUsuario nivelUsuario);

    NivelUsuario buscar(int idUsuario, int idNivel);

    ArrayList<NivelUsuario> buscarPorUsuario(int idUsuario);

    ArrayList<NivelUsuario> listarTodos();

    void excluir(int idUsuario, int idNivel);
}
