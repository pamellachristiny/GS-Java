package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Professor;
import br.com.fiap.biblioteca.repositorio.RepositorioProfessor;

import java.sql.*;
import java.util.ArrayList;

public class ProfessorDAO implements RepositorioProfessor {

    private Connection conexao;

    public ProfessorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void salvar(Professor professor) {
        String sql = "INSERT INTO tb_professor (id_professor, nome_professor, especialidade_professor, email_professor, senha_professor) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, professor.getId_professor());
            stmt.setString(2, professor.getNome_professor());
            stmt.setString(3, professor.getEspecialidade_professor());
            stmt.setString(4, professor.getEmail_professor());
            stmt.setString(5, professor.getSenha_professor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Professor buscarPorId(int idProfessor) {
        String sql = "SELECT * FROM tb_professor WHERE id_professor = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearProfessor(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Professor buscarPorEmail(String email) {
        String sql = "SELECT * FROM tb_professor WHERE email_professor = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearProfessor(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Professor> listarTodos() {
        String sql = "SELECT * FROM tb_professor";

        ArrayList<Professor> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearProfessor(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void atualizar(Professor professor) {
        String sql = "UPDATE tb_professor SET nome_professor = ?, especialidade_professor = ?, email_professor = ?, senha_professor = ? " +
                "WHERE id_professor = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome_professor());
            stmt.setString(2, professor.getEspecialidade_professor());
            stmt.setString(3, professor.getEmail_professor());
            stmt.setString(4, professor.getSenha_professor());
            stmt.setInt(5, professor.getId_professor());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tb_professor WHERE id_professor = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Professor mapearProfessor(ResultSet rs) throws SQLException {
        return new Professor(
                rs.getInt("id_professor"),
                rs.getString("nome_professor"),
                rs.getString("especialidade_professor"),
                rs.getString("email_professor"),
                rs.getString("senha_professor")
        );
    }
}
