package com.linkedrh.crud.controller;

import com.linkedrh.crud.model.Turma;
import com.linkedrh.crud.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private TurmaService turmaService = new TurmaService();

    // Criando uma nova turma
    @PostMapping
    public void criarTurma(@RequestBody Turma turma) throws SQLException {
        turmaService.salvar(turma);
    }

    // Listar por curso.
    @GetMapping("/curso/{codigoCurso}")
    public List<Turma> listarTurmas(@PathVariable int codigoCurso) throws SQLException {
        return turmaService.listarPorCurso(codigoCurso);
    }

    // Atualizar turma não alterando o curso.
    @PutMapping("/{codigo}")
    public void atualizarTurma(@PathVariable int codigo, @RequestBody Turma turma) throws SQLException {
        Turma turmaExistente = turmaService.buscarPorId(codigo);
        if (turmaExistente == null) {
            throw new RuntimeException("Turma não encontrada com código: " + codigo);
        }

        turma.setCodigo(codigo);
        turma.setCurso(turmaExistente.getCurso());

        turmaService.atualizar(turma);
    }

    // Excluir turma
    @DeleteMapping("/{codigo}")
    public void excluirTurma(@PathVariable int codigo) throws SQLException {
        turmaService.excluir(codigo);
    }
    @PostMapping("/{turmaId}/participantes/{funcionarioId}")
    public void adicionarParticipante(@PathVariable int turmaId, @PathVariable int funcionarioId) throws SQLException {
        turmaService.adicionarParticipante(turmaId, funcionarioId);
    }
}
