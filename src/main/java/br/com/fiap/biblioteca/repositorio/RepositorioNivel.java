package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Nivel;
import java.util.ArrayList;

public interface RepositorioNivel {

    void salvar(Nivel nivel);

    Nivel buscarPorId(int idNivel);

    Nivel buscarPorNome(String nome_nivel);

    ArrayList<Nivel> listarTodos();

    void atualizar(Nivel nivel);

    void excluir(int idNivel);
}
