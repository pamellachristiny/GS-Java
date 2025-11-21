package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Challenge;
import br.com.fiap.biblioteca.repositorio.RepositorioChallenge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDAO implements RepositorioChallenge{

    public ChallengeDAO() {
    }

    @Override
    public void salvar(Challenge challenge) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "INSERT INTO TB_CHALLENGE (id_challenge, id_usuario, id_curso, nome_challenge, descricao_challenge) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, obterProximoIdChallenge());
            comando.setLong(2, challenge.getId_usuario());
            comando.setLong(3, challenge.getId_curso());
            comando.setString(4, challenge.getNome_challenge());
            comando.setString(5, challenge.getDescricao_challenge());

            comando.executeUpdate();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar challenge", e);
        }
    }

    private Long obterProximoIdChallenge() {
        Long id = null;
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT SEQ_CHALLENGE_ID.NEXTVAL FROM DUAL";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                id = rs.getLong(1);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar ID de challenge", e);
        }
        return id;
    }

    @Override
    public Challenge buscarPorId(Long idChallenge) {
        Challenge challenge = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CHALLENGE WHERE id_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, idChallenge);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                challenge = new Challenge(
                        rs.getLong("id_challenge"),
                        rs.getLong("id_usuario"),
                        rs.getLong("id_curso"),
                        rs.getString("nome_challenge"),
                        rs.getString("descricao_challenge")
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar challenge por id", e);
        }

        return challenge;
    }

    @Override
    public List<Challenge> buscarPorUsuario(Long idUsuario) {
        List<Challenge> challenges = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CHALLENGE WHERE id_usuario = ? ORDER BY id_challenge";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, idUsuario);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                challenges.add(new Challenge(
                        rs.getLong("id_challenge"),
                        rs.getLong("id_usuario"),
                        rs.getLong("id_curso"),
                        rs.getString("nome_challenge"),
                        rs.getString("descricao_challenge")
                ));
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar challenges por usuário", e);
        }

        return challenges;
    }

    @Override
    public List<Challenge> buscarPorCurso(Long idCurso) {
        List<Challenge> challenges = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CHALLENGE WHERE id_curso = ? ORDER BY id_challenge";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, idCurso);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                challenges.add(new Challenge(
                        rs.getLong("id_challenge"),
                        rs.getLong("id_usuario"),
                        rs.getLong("id_curso"),
                        rs.getString("nome_challenge"),
                        rs.getString("descricao_challenge")
                ));
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar challenges por curso", e);
        }

        return challenges;
    }

    @Override
    public List<Challenge> listarTodos() {
        List<Challenge> challenges = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CHALLENGE ORDER BY id_challenge";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                challenges.add(new Challenge(
                        rs.getLong("id_challenge"),
                        rs.getLong("id_usuario"),
                        rs.getLong("id_curso"),
                        rs.getString("nome_challenge"),
                        rs.getString("descricao_challenge")
                ));
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar challenges", e);
        }

        return challenges;
    }

    @Override
    public void excluir(Long idChallenge) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM TB_CHALLENGE WHERE id_challenge = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, idChallenge);

            comando.executeUpdate();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir challenge", e);
        }
    }
}
