package com.linkedrh.crud.dao;
import com.linkedrh.crud.model.Funcionario;
import com.linkedrh.crud.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public List<Funcionario>ListarFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultado = preparedStatement.executeQuery()) {
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(resultado.getInt("codigo"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setNascimento(resultado.getDate("nascimento").toLocalDate());
                funcionario.setCargo(resultado.getString("cargo"));
                funcionario.setAdmissao(resultado.getDate("admissao").toLocalDate());
                funcionario.setStatus(resultado.getBoolean("status"));

                funcionarios.add(funcionario);
            }

        }

        System.out.println("Funcionarios listados com sucesso: " + funcionarios.size());
        return funcionarios;
    }
}
