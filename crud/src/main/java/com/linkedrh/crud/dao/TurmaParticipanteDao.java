package com.linkedrh.crud.dao;
import com.linkedrh.crud.model.TurmaParticipante;
import com.linkedrh.crud.util.ConnectionFactory;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TurmaParticipanteDao {

    //adicionar participante a uma turma.
    public void adicionarParticipante(TurmaParticipante turmaParticipante) throws  SQLException {
        String sql = "INSERT INTO turma_participante(turma,funcionario) values(?,?)";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,turmaParticipante.getTurma());
            preparedStatement.setInt(2,turmaParticipante.getFuncionario());

            preparedStatement.execute();
            System.out.println("Participante adicionado com sucesso! ");
        }
    }

    //remover participante da turma.

    public void RemoverParticipante(int turmaId, int funcionarioId) throws SQLException {
        String sql = "DELETE FROM turma_participante WHERE turma=? AND funcionario=?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,turmaId);
            preparedStatement.setInt(2,funcionarioId);

            int linhas = preparedStatement.executeUpdate();
            if(linhas>0){
                System.out.println("Participante removido com sucesso! ");
            }else {
                System.out.println("Nenhum participante para remover. ");
            }
        }

    }

    //Listar participantes da turma.

    public List<TurmaParticipante> listarParticipantes(int turmaId) throws SQLException {
        List<TurmaParticipante> lista =  new ArrayList<>();
        String sql = "SELECT * FROM turma_participante WHERE turma=?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,turmaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TurmaParticipante turmaParticipante = new TurmaParticipante();
                    turmaParticipante.setCodigo(resultSet.getInt("codigo"));
                    turmaParticipante.setTurma(resultSet.getInt("turma"));
                    turmaParticipante.setFuncionario(resultSet.getInt("funcionario"));

                    lista.add(turmaParticipante);
                }
            }
        }

        System.out.println("Total de participantes na turma: " + turmaId + ": " + lista.size());
        return lista;
    }
}
