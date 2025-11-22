package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Conversa;
import br.com.fiap.biblioteca.repositorio.RepositorioConversa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO implements RepositorioConversa {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "usuario", "senha");
    }

    @Override
    public void salvar(Conversa conversa) {
        String sql = "INSERT INTO CONVERSAS (ID_CONVERSA, ID_USUARIO, DATA_HORA, CONTEUDO) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, conversa.getIdConversa());
            stmt.setInt(2, conversa.getIdUsuario());
            stmt.setTimestamp(3, new Timestamp(conversa.getDataHora().getTime()));
            stmt.setString(4, conversa.getConteudo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Conversa buscarPorId(String idConversa) {
        String sql = "SELECT * FROM CONVERSAS WHERE ID_CONVERSA = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idConversa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Conversa(
                        rs.getString("ID_CONVERSA"),
                        rs.getInt("ID_USUARIO"),
                        rs.getTimestamp("DATA_HORA"),
                        rs.getString("CONTEUDO")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Conversa> buscarPorUsuario(int id_usuario) {
        String sql = "SELECT * FROM CONVERSAS WHERE ID_USUARIO = ?";
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id_usuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                conversas.add(new Conversa(
                        rs.getString("ID_CONVERSA"),
                        rs.getInt("ID_USUARIO"),
                        rs.getTimestamp("DATA_HORA"),
                        rs.getString("CONTEUDO")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conversas;
    }

    @Override
    public List<Conversa> listarTodas() {
        String sql = "SELECT * FROM CONVERSAS";
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                conversas.add(new Conversa(
                        rs.getString("ID_CONVERSA"),
                        rs.getInt("ID_USUARIO"),
                        rs.getTimestamp("DATA_HORA"),
                        rs.getString("CONTEUDO")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conversas;
    }

    @Override
    public void excluir(String idConversa) {
        String sql = "DELETE FROM CONVERSAS WHERE ID_CONVERSA = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idConversa);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Conversa conversa) {
        String sql = "UPDATE CONVERSAS SET ID_USUARIO = ?, DATA_HORA = ?, CONTEUDO = ? WHERE ID_CONVERSA = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, conversa.getIdUsuario());
            stmt.setTimestamp(2, new Timestamp(conversa.getDataHora().getTime()));
            stmt.setString(3, conversa.getConteudo());
            stmt.setString(4, conversa.getIdConversa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
