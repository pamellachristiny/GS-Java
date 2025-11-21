package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.ConversaIndicaChallenge;

import java.util.ArrayList;

public interface RepositorioConversaIndicaChallenge {

    void salvar(ConversaIndicaChallenge conversa);

    ArrayList<ConversaIndicaChallenge> buscarTodos();

    ConversaIndicaChallenge buscarPorId(int id);

    ArrayList<ConversaIndicaChallenge> buscarPorIdConversa(String idConversa);

    ArrayList<ConversaIndicaChallenge> buscarPorIdChallenge(int idChallenge);

    void atualizar(ConversaIndicaChallenge conversa);

    void excluir(int id);
}

