package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Nivel;
import java.util.ArrayList;

public interface RepositorioNivel {

    void salvar(Nivel nivel);

    Nivel buscarPorId(int idNivel);

    Nivel buscarPorNome(String nomeNivel);

    ArrayList<Nivel> listarTodos();

    void excluir(int idNivel);
}
