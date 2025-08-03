package com.linkedrh.crud.dao;

import com.linkedrh.crud.model.Turma;
import com.linkedrh.crud.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDao {
    private Turma mapTurma(ResultSet rs) throws SQLException {
        Turma turma = new Turma();
        turma.setCodigo(rs.getInt("codigo"));
        turma.setInicio(rs.getDate("inicio").toLocalDate());
        turma.setFim(rs.getDate("fim").toLocalDate());
        turma.setLocal(rs.getString("local"));
        turma.setCurso(rs.getInt("curso"));
        return turma;
    }


    // Criar nova turma.
    public void salvarTurma(Turma turma) throws SQLException {
        String sql = "INSERT INTO turma (inicio, fim, local, curso) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, Date.valueOf(turma.getInicio()));
            preparedStatement.setDate(2, Date.valueOf(turma.getFim()));
            preparedStatement.setString(3, turma.getLocal());
            preparedStatement.setInt(4, turma.getCurso());

            preparedStatement.executeUpdate();
            System.out.println("Turma salva com sucesso!!");
        }
    }

    // Listar todas as turmas.
    public List<Turma> listarTodasTurmas() throws SQLException {
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT * FROM turma ORDER BY inicio ASC, fim ASC";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultado = preparedStatement.executeQuery()) {

            while (resultado.next()) {
                turmas.add(mapTurma(resultado));
            }
        }
        return turmas;
    }

    // Listar turmas por curso.
    public List<Turma> listarTurmasPorCurso(int codigoCurso) throws SQLException {
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT * FROM turma WHERE curso = ? ORDER BY inicio ASC, fim ASC";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCurso);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                while (resultado.next()) {
                    turmas.add(mapTurma(resultado));
                }
            }
        }

        return turmas;
    }

    // Buscar turma por Id.
    public Turma buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM turma WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    return mapTurma(resultado);
                }
            }
        }
        return null;
    }

    // Atualizar turma não alterando o curso.
    public void atualizarTurma(Turma turma) throws SQLException {
        String sql = "UPDATE turma SET inicio = ?, fim = ?, local = ? WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, Date.valueOf(turma.getInicio()));
            preparedStatement.setDate(2, Date.valueOf(turma.getFim()));
            preparedStatement.setString(3, turma.getLocal());
            preparedStatement.setInt(4, turma.getCodigo());

            int linhasAfetadas = preparedStatement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Turma atualizada com sucesso!!");
            } else {
                System.out.println("Nenhuma turma encontrada para atualizar.");
            }
        }
    }

    // Excluir turma por código.
    public void excluirTurma(int codigo) throws SQLException {
        String sql = "DELETE FROM turma WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigo);
            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Turma excluída com sucesso!!");
            } else {
                System.out.println("Nenhuma turma encontrada para excluir.");
            }
        }
    }

    // Excluir todas as turmas de um curso.
    public void excluirTurmasPorCurso(int codigoCurso) throws SQLException {
        String sql = "DELETE FROM turma WHERE curso = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCurso);
            int linhasAfetadas = preparedStatement.executeUpdate();
            System.out.println("Turmas excluídas para o curso ");
        }
    }


    // Adicionar participante em uma turma.
    public void adicionarParticipante(int turmaId, int funcionarioId) throws SQLException {
        String sql = "INSERT INTO turmaparticipante (turma, funcionario) VALUES (?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, turmaId);
            preparedStatement.setInt(2, funcionarioId);
            preparedStatement.executeUpdate();
            System.out.println("Participante adicionado à turma!!");
        }
    }

    // Remover participante de uma turma.
    public void removerParticipante(int turmaId, int funcionarioId) throws SQLException {
        String sql = "DELETE FROM turmaparticipante WHERE turma = ? AND funcionario = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, turmaId);
            preparedStatement.setInt(2, funcionarioId);
            preparedStatement.executeUpdate();
            System.out.println("Participante removido da turma!");
        }
    }

    // Listar participantes de uma turma.
    public List<Integer> listarParticipantes(int turmaId) throws SQLException {
        List<Integer> participantes = new ArrayList<>();
        String sql = "SELECT funcionario FROM turmaparticipante WHERE turma = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, turmaId);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                while (resultado.next()) {
                    participantes.add(resultado.getInt("funcionario"));
                }
            }
        }
        return participantes;
    }
}
