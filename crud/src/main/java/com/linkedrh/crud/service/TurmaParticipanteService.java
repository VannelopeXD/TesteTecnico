package com.linkedrh.crud.service;

import com.linkedrh.crud.dao.TurmaParticipanteDao;
import com.linkedrh.crud.model.TurmaParticipante;

import java.sql.SQLException;
import java.util.List;

public class TurmaParticipanteService {

    private TurmaParticipanteDao dao = new TurmaParticipanteDao();

    public void adicionar(TurmaParticipante tp) throws SQLException {
        dao.adicionarParticipante(tp);
    }

    public void remover(int turmaId, int funcionarioId) throws SQLException {
        dao.RemoverParticipante(turmaId, funcionarioId);
    }

    public List<TurmaParticipante> listar(int turmaId) throws SQLException {
        return dao.listarParticipantes(turmaId);
    }
}
