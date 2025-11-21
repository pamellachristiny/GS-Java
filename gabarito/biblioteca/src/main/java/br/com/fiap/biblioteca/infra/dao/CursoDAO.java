package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.dominio.Curso;
import br.com.fiap.biblioteca.repositorio.RepositorioCurso;

import java.sql.*;
import java.util.ArrayList;

public class CursoDAO implements RepositorioCurso {

    public CursoDAO() {}

    @Override
    public void salvar(Curso curso) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "INSERT INTO TB_CURSO (id_curso, nome_curso, id_professor, id_challenge) " +
                    "VALUES (SEQ_CURSO_ID.NEXTVAL, ?, ?, ?)";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, curso.getNomeCurso());
            comando.setLong(2, curso.getIdProfessor());
            comando.setLong(3, curso.getIdChallenge());

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar curso", e);
        }
    }

    @Override
    public ArrayList<Curso> buscarTodos() {
        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CURSO ORDER BY id_curso";

            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("id_curso"),
                        rs.getString("nome_curso"),
                        rs.getLong("id_professor"),
                        rs.getLong("id_challenge")
                );
                cursos.add(curso);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cursos", e);
        }

        return cursos;
    }

    @Override
    public Curso buscarPorId(Long id) {
        Curso curso = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CURSO WHERE id_curso = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                curso = new Curso(
                        rs.getLong("id_curso"),
                        rs.getString("nome_curso"),
                        rs.getLong("id_professor"),
                        rs.getLong("id_challenge")
                );
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar curso por ID", e);
        }

        return curso;
    }

    @Override
    public ArrayList<Curso> buscarPorProfessor(Long idProfessor) {
        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM TB_CURSO WHERE id_professor = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, idProfessor);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("id_curso"),
                        rs.getString("nome_curso"),
                        rs.getLong("id_professor"),
                        rs.getLong("id_challenge")
                );
                cursos.add(curso);
            }

            rs.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cursos por professor", e);
        }

        return cursos;
    }

    @Override
    public void atualizar(Curso curso) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "UPDATE TB_CURSO SET nome_curso = ?, id_professor = ?, id_challenge = ? " +
                    "WHERE id_curso = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, curso.getNomeCurso());
            comando.setLong(2, curso.getIdProfessor());
            comando.setLong(3, curso.getIdChallenge());
            comando.setLong(4, curso.getIdCurso());

            comando.executeUpdate();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar curso", e);
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM TB_CURSO WHERE id_curso = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar curso", e);
        }
    }
}
