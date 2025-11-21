package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Challenge;
import java.util.List;

public interface RepositorioChallenge {

    void salvar(Challenge challenge);

    Challenge buscarPorId(Long idChallenge);

    List<Challenge> buscarPorUsuario(Long idUsuario);

    List<Challenge> buscarPorCurso(Long idCurso);

    List<Challenge> listarTodos();

    void excluir(Long idChallenge);
}

