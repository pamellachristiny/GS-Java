package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.NivelUsuario;
import br.com.fiap.biblioteca.repositorio.RepositorioNivelUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class NivelUsuarioDAO implements RepositorioNivelUsuario{

    public NivelUsuarioDAO() {}

    @Override
    public void salvar(NivelUsuario nivelUsuario) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "INSERT INTO TB_NIVEL_USUARIO " +
                    "(id_nivel_usuario, id_usuario, id_nivel, data_hora_nivel) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, obterProximoId());
            comando.setInt(2, nivelUsuario.getId_usuario());
            comando.setInt(3, nivelUsuario.getId_nivel());
            comando.setTimestamp(4, new Timestamp(nivelUsuario.getData_hora_nivel().getTime()));

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar nível do usuário", e);
        }
    }


    public NivelUsuario buscarPorIdUsuario(int idUsuario) {
        return null;
    }


    public ArrayList<NivelUsuario> buscarPorIdNivel(int idNivel) {
        return null;
    }

    private Long obterProximoId() {
        Long id = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT SEQ_NIVEL_USUARIO_ID.NEXTVAL FROM DUAL";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                id = rs.getLong(1);
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
    public NivelUsuario buscar(int idUsuario, int idNivel) {
        NivelUsuario nivelUsuario = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_NIVEL_USUARIO " +
                    "WHERE id_usuario = ? AND id_nivel = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idUsuario);
            comando.setInt(2, idNivel);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                nivelUsuario = new NivelUsuario(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_nivel"),
                        new Date(rs.getTimestamp("data_hora_nivel").getTime())
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar nível do usuário", e);
        }

        return nivelUsuario;
    }

    @Override
    public ArrayList<NivelUsuario> buscarPorUsuario(int idUsuario) {
        ArrayList<NivelUsuario> lista = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_NIVEL_USUARIO WHERE id_usuario = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idUsuario);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                NivelUsuario nivelUsuario = new NivelUsuario(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_nivel"),
                        new Date(rs.getTimestamp("data_hora_nivel").getTime())
                );

                lista.add(nivelUsuario);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar níveis por usuário", e);
        }

        return lista;
    }

    @Override
    public ArrayList<NivelUsuario> listarTodos() {
        ArrayList<NivelUsuario> lista = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_NIVEL_USUARIO ORDER BY id_usuario, id_nivel";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                NivelUsuario nivelUsuario = new NivelUsuario(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_nivel"),
                        new Date(rs.getTimestamp("data_hora_nivel").getTime())
                );

                lista.add(nivelUsuario);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar níveis dos usuários", e);
        }

        return lista;
    }

    @Override
    public void excluir(int idUsuario, int idNivel) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM TB_NIVEL_USUARIO WHERE id_usuario = ? AND id_nivel = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idUsuario);
            comando.setInt(2, idNivel);

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir nível do usuário", e);
        }
    }
    @Override
    public void atualizar(NivelUsuario nivelUsuario) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "UPDATE TB_NIVEL_USUARIO SET data_hora_nivel = ? " +
                    "WHERE id_usuario = ? AND id_nivel = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setTimestamp(1, new Timestamp(nivelUsuario.getData_hora_nivel().getTime()));
            comando.setInt(2, nivelUsuario.getId_usuario());
            comando.setInt(3, nivelUsuario.getId_nivel());

            comando.executeUpdate();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar nível do usuário", e);
        }
    }

}
