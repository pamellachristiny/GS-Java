package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.dominio.Conversa;
import br.com.fiap.biblioteca.repositorio.RepositorioConversa;
import java.util.List;

public class ConversaService {

    private RepositorioConversa repositorioConversas;

    public ConversaService(RepositorioConversa repositorioConversas) {
        this.repositorioConversas = repositorioConversas;
    }

    public void adicionar(Conversa conversa) {
        repositorioConversas.salvar(conversa);
    }

    public Conversa buscarPorId(String id) {
        return repositorioConversas.buscarPorId(id);
    }

    public List<Conversa> buscarPorUsuario(int idUsuario) {
        return repositorioConversas.buscarPorUsuario(idUsuario);
    }

    public List<Conversa> listarTodas() {
        return repositorioConversas.listarTodas();
    }

    public void excluir(String id) {
        repositorioConversas.excluir(id);
    }
}

