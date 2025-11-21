package br.com.fiap.biblioteca.repositorio;

import br.com.fiap.biblioteca.dominio.Conversa;
import java.util.List;

public interface RepositorioConversa {

    void salvar(Conversa conversa);

    Conversa buscarPorId(String idConversa);

    List<Conversa> buscarPorUsuario(int idUsuario);

    List<Conversa> listarTodas();

    void excluir(String idConversa);
}
