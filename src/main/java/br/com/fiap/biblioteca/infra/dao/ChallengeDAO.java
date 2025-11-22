package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Challenge;
import br.com.fiap.biblioteca.repositorio.RepositorioChallenge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDAO implements RepositorioChallenge {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "usuario",
                "senha"
        );
    }

    @Override
    public void salvar(Challenge challenge) {
        String sql = "INSERT INTO CHALLENGE (ID_USUARIO, ID_CURSO, NOME_CHALLENGE, DESCRICAO_CHALLENGE) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, challenge.getId_usuario());
            ps.setLong(2, challenge.getId_curso());
            ps.setString(3, challenge.getNome_challenge());
            ps.setString(4, challenge.getDescricao_challenge());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Challenge buscarPorId(Long id) {
        String sql = "SELECT * FROM CHALLENGE WHERE ID_CHALLENGE = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Challenge(
                        rs.getLong("ID_CHALLENGE"),
                        rs.getLong("ID_USUARIO"),
                        rs.getLong("ID_CURSO"),
                        rs.getString("NOME_CHALLENGE"),
                        rs.getString("DESCRICAO_CHALLENGE")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Challenge> buscarPorUsuario(Long idUsuario) {
        String sql = "SELECT * FROM CHALLENGE WHERE ID_USUARIO = ?";
        List<Challenge> lista = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Challenge(
                        rs.getLong("ID_CHALLENGE"),
                        rs.getLong("ID_USUARIO"),
                        rs.getLong("ID_CURSO"),
                        rs.getString("NOME_CHALLENGE"),
                        rs.getString("DESCRICAO_CHALLENGE")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public List<Challenge> buscarPorCurso(Long idCurso) {
        String sql = "SELECT * FROM CHALLENGE WHERE ID_CURSO = ?";
        List<Challenge> lista = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idCurso);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Challenge(
                        rs.getLong("ID_CHALLENGE"),
                        rs.getLong("ID_USUARIO"),
                        rs.getLong("ID_CURSO"),
                        rs.getString("NOME_CHALLENGE"),
                        rs.getString("DESCRICAO_CHALLENGE")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public List<Challenge> listarTodos() {
        String sql = "SELECT * FROM CHALLENGE";
        List<Challenge> lista = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Challenge(
                        rs.getLong("ID_CHALLENGE"),
                        rs.getLong("ID_USUARIO"),
                        rs.getLong("ID_CURSO"),
                        rs.getString("NOME_CHALLENGE"),
                        rs.getString("DESCRICAO_CHALLENGE")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public void atualizar(Challenge challenge) {
        String sql = "UPDATE CHALLENGE SET ID_USUARIO = ?, ID_CURSO = ?, NOME_CHALLENGE = ?, DESCRICAO_CHALLENGE = ? WHERE ID_CHALLENGE = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, challenge.getId_usuario());
            ps.setLong(2, challenge.getId_curso());
            ps.setString(3, challenge.getNome_challenge());
            ps.setString(4, challenge.getDescricao_challenge());
            ps.setLong(5, challenge.getId_challenge());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM CHALLENGE WHERE ID_CHALLENGE = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
