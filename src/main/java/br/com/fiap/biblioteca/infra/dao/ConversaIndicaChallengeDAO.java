package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.ConversaIndicaChallenge;
import br.com.fiap.biblioteca.repositorio.RepositorioConversaIndicaChallenge;

import java.sql.*;
import java.util.ArrayList;

public class ConversaIndicaChallengeDAO implements RepositorioConversaIndicaChallenge {

    public ConversaIndicaChallengeDAO() {}

    @Override
    public void salvar(ConversaIndicaChallenge conversa) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "INSERT INTO TB_CONVERSA_INDICA_CHALLENGE " +
                    "(id_conversa_indica_challenge, id_conversa, id_challenge) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, obterProximoId());
            comando.setString(2, conversa.getId_conversa());
            comando.setInt(3, conversa.getId_challenge());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar ConversaIndicaChallenge", e);
        }
    }

    private int obterProximoId() {
        int id = 0;
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT SEQ_CONVERSA_INDICA_CHALLENGE_ID.NEXTVAL FROM DUAL";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar ID", e);
        }
        return id;
    }

    @Override
    public ConversaIndicaChallenge buscarPorId(int idBusca) {
        ConversaIndicaChallenge conversa = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CONVERSA_INDICA_CHALLENGE WHERE id_conversa_indica_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idBusca);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                conversa = new ConversaIndicaChallenge(
                        rs.getInt("id_conversa_indica_challenge"),
                        rs.getString("id_conversa"),
                        rs.getInt("id_challenge")
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por ID", e);
        }

        return conversa;
    }

    @Override
    public ArrayList<ConversaIndicaChallenge> buscarPorIdConversa(String idConversa) {
        ArrayList<ConversaIndicaChallenge> lista = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CONVERSA_INDICA_CHALLENGE WHERE id_conversa = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, idConversa);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                ConversaIndicaChallenge conversa = new ConversaIndicaChallenge(
                        rs.getInt("id_conversa_indica_challenge"),
                        rs.getString("id_conversa"),
                        rs.getInt("id_challenge")
                );
                lista.add(conversa);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por ID_CONVERSA", e);
        }

        return lista;
    }

    @Override
    public ArrayList<ConversaIndicaChallenge> buscarPorIdChallenge(int idChallenge) {
        ArrayList<ConversaIndicaChallenge> lista = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CONVERSA_INDICA_CHALLENGE WHERE id_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idChallenge);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                ConversaIndicaChallenge conversa = new ConversaIndicaChallenge(
                        rs.getInt("id_conversa_indica_challenge"),
                        rs.getString("id_conversa"),
                        rs.getInt("id_challenge")
                );
                lista.add(conversa);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por ID_CHALLENGE", e);
        }

        return lista;
    }

    @Override
    public ArrayList<ConversaIndicaChallenge> buscarTodos() {
        ArrayList<ConversaIndicaChallenge> lista = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CONVERSA_INDICA_CHALLENGE ORDER BY id_conversa_indica_challenge";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                ConversaIndicaChallenge conversa = new ConversaIndicaChallenge(
                        rs.getInt("id_conversa_indica_challenge"),
                        rs.getString("id_conversa"),
                        rs.getInt("id_challenge")
                );
                lista.add(conversa);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ConversaIndicaChallenge", e);
        }

        return lista;
    }

    @Override
    public void atualizar(ConversaIndicaChallenge conversa) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "UPDATE TB_CONVERSA_INDICA_CHALLENGE " +
                    "SET id_conversa = ?, id_challenge = ? " +
                    "WHERE id_conversa_indica_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, conversa.getId_conversa());
            comando.setInt(2, conversa.getId_challenge());
            comando.setInt(3, conversa.getId_conversa_indica_challenge());

            comando.executeUpdate();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ConversaIndicaChallenge", e);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM TB_CONVERSA_INDICA_CHALLENGE WHERE id_conversa_indica_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ConversaIndicaChallenge", e);
        }
    }
}
