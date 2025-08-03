package com.linkedrh.crud.dao;

import com.linkedrh.crud.model.Curso;
import com.linkedrh.crud.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    public void salvarCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nome,descricao,duracao) VALUES (?,?,?)";

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setInt(3, curso.getDuracao());

            preparedStatement.executeUpdate();
            System.out.println("Curso Salvo com sucesso! " + curso.getNome());
        }
    }

    public List<Curso> listarCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso ORDER BY nome";

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultado = preparedStatement.executeQuery()) {

            while (resultado.next()) {
                Curso curso = new Curso();
                curso.setCodigo(resultado.getInt("codigo"));
                curso.setNome(resultado.getString("nome"));
                curso.setDescricao(resultado.getString("descricao"));
                curso.setDuracao(resultado.getInt("duracao"));

                cursos.add(curso);
            }
        }
        System.out.println("Cursos listados com sucesso! " + cursos.size());
        return cursos;
    }

    public void AlterarCurso(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, duracao = ? WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setInt(3, curso.getDuracao());
            preparedStatement.setInt(4, curso.getCodigo());

            int linhasAlteradas = preparedStatement.executeUpdate();

            if (linhasAlteradas > 0) {
                System.out.println("Curso atualizado com sucesso! " + curso.getNome());
            } else {
                System.out.println("Nenhum curso encontrado com o seguinte codigo: " + curso.getCodigo());
            }
        }
    }
    public void excluirCurso(Curso curso) throws SQLException {
        String sql = "DELETE FROM curso WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, curso.getCodigo());
            int linhasExcluidas = preparedStatement.executeUpdate();

            if (linhasExcluidas > 0) {
                System.out.println("Curso excluido com sucesso:  " + curso.getNome());
            } else {
                System.out.println("Nenhum curso encontrado: " + curso.getNome());
            }
        }
    }

}
