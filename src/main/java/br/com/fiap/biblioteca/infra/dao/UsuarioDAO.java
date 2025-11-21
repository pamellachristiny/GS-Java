package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Usuario;
import br.com.fiap.biblioteca.repositorio.RepositorioUsuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO implements RepositorioUsuario {

    public UsuarioDAO() {
    }

    @Override
    public void salvar(Usuario usuario) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, plano_usuario) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, obterProximoIdUsuario());
            comando.setString(2, usuario.getNome_usuario());
            comando.setString(3, usuario.getEmail_usuario());
            comando.setString(4, usuario.getSenha_usuario());
            comando.setString(5, usuario.getPlano_usuario());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }

    private Long obterProximoIdUsuario() {
        Long id = null;
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT SEQ_USUARIO_ID.NEXTVAL FROM DUAL";

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
    public Usuario buscarPorId(int idUsuario) {
        Usuario usuario = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_USUARIO WHERE id_usuario = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, idUsuario);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("plano_usuario")
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por id", e);
        }

        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_USUARIO WHERE email_usuario = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, email);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("plano_usuario")
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email", e);
        }

        return usuario;
    }

    @Override
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_USUARIO ORDER BY id_usuario";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("plano_usuario")
                );

                usuarios.add(usuario);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }

        return usuarios;
    }

    @Override
    public void excluir(int id) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM TB_USUARIO WHERE id_usuario = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário", e);
        }
    }
}
