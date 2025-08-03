package com.linkedrh.crud.controller;

import com.linkedrh.crud.dao.TurmaParticipanteDao;
import com.linkedrh.crud.model.TurmaParticipante;
import com.linkedrh.crud.service.TurmaParticipanteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas/{turmaId}/participantes")
public class TurmaParticipanteController {

    private TurmaParticipanteService service = new TurmaParticipanteService();

    //adicionar Participante
    @PostMapping
    public void adicionarParticipante(@PathVariable int turmaId, @RequestBody TurmaParticipante participante) throws SQLException{
        participante.setTurma(turmaId);
        service.adicionar(participante);
    }

    //Listar participantes da turma.
    @GetMapping
    public List<TurmaParticipante> listarParticipantes(@PathVariable int turmaId) throws SQLException{
        return service.listar(turmaId);
    }

    @DeleteMapping("{funcionarioId}")
    public void removerParticipante(@PathVariable int turmaId, @PathVariable int funcionarioId) throws SQLException{
        service.remover(turmaId,funcionarioId);
    }

}
