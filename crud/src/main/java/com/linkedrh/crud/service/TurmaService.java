package com.linkedrh.crud.service;

import com.linkedrh.crud.dao.TurmaDao;
import com.linkedrh.crud.model.Turma;

import java.sql.SQLException;
import java.util.List;

public class TurmaService {

    private final TurmaDao turmaDao = new TurmaDao();

    // Criar turma
    public void salvar(Turma turma) throws SQLException {
        turmaDao.salvarTurma(turma);
    }

    // Listar as turmas completas.
    public List<Turma> listar() throws SQLException {
        return turmaDao.listarTodasTurmas();
    }

    // Listar turmas por curso.
    public List<Turma> listarPorCurso(int codigoCurso) throws SQLException {
        return turmaDao.listarTurmasPorCurso(codigoCurso);
    }

    // Buscar turma pelo id.
    public Turma buscarPorId(int codigo) throws SQLException {
        return turmaDao.buscarPorId(codigo);
    }

    // Atualizar turma.
    public void atualizar(Turma turma) throws SQLException {
        Turma turmaExistente = turmaDao.buscarPorId(turma.getCodigo());

        if (turmaExistente == null) {
            throw new SQLException("Turma não encontrada!");
        }

        if (turma.getCurso() != turmaExistente.getCurso()) {
            throw new SQLException("Não é permitido alterar o curso de uma turma existente!");
        }

        turmaDao.atualizarTurma(turma);
    }

    // Excluir turma.
    public void excluir(int codigo) throws SQLException {
        turmaDao.excluirTurma(codigo);
    }

    // Adicionar participante.
    public void adicionarParticipante(int turmaId, int funcionarioId) throws SQLException {
        turmaDao.adicionarParticipante(turmaId, funcionarioId);
    }

    // Remover participante.
    public void removerParticipante(int turmaId, int funcionarioId) throws SQLException {
        turmaDao.removerParticipante(turmaId, funcionarioId);
    }
}
