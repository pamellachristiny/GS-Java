package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Conversa;
import br.com.fiap.biblioteca.repositorio.RepositorioConversa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO implements RepositorioConversa {

    private Connection connection;

    public ConversaDAO() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    "rm565206",
                    "200806"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }

    @Override
    public void salvar(Conversa conversa) {
        String sql = "INSERT INTO TB_CONVERSA (id_conversa, id_usuario, data_hora_conversa, conteudo_conversa) " +
                "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conversa.getIdConversa());
            stmt.setInt(2, conversa.getIdUsuario());
            stmt.setString(3, conversa.getDataHora());
            stmt.setString(4, conversa.getConteudo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar conversa", e);
        }
    }

    @Override
    public Conversa buscarPorId(String idConversa) {
        String sql = "SELECT * FROM TB_CONVERSA WHERE id_conversa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idConversa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Conversa(
                        rs.getString("id_conversa"),
                        rs.getInt("id_usuario"),
                        rs.getString("data_hora_conversa"),
                        rs.getString("conteudo_conversa")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conversa", e);
        }
    }

    @Override
    public List<Conversa> buscarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM TB_CONVERSA WHERE id_usuario = ? ORDER BY data_hora_conversa DESC";
        List<Conversa> conversas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                conversas.add(new Conversa(
                        rs.getString("id_conversa"),
                        rs.getInt("id_usuario"),
                        rs.getString("data_hora_conversa"),
                        rs.getString("conteudo_conversa")
                ));
            }

            return conversas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conversas do usuário", e);
        }
    }

    @Override
    public List<Conversa> listarTodas() {
        String sql = "SELECT * FROM TB_CONVERSA ORDER BY data_hora_conversa DESC";
        List<Conversa> conversas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                conversas.add(new Conversa(
                        rs.getString("id_conversa"),
                        rs.getInt("id_usuario"),
                        rs.getString("data_hora_conversa"),
                        rs.getString("conteudo_conversa")
                ));
            }

            return conversas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar conversas", e);
        }
    }

    @Override
    public void excluir(String idConversa) {
        String sql = "DELETE FROM TB_CONVERSA WHERE id_conversa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idConversa);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir conversa", e);
        }
    }
}
