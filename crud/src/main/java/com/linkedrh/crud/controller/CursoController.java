package com.linkedrh.crud.controller;
import com.linkedrh.crud.dao.CursoDao;
import com.linkedrh.crud.model.Curso;
import com.linkedrh.crud.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")

public class CursoController {
    private CursoService  cursoService = new CursoService();

    @PostMapping
    public Curso criarCurso(@RequestBody Curso curso) throws SQLException {
        cursoService.salvar(curso);
        return curso;
    }

    @GetMapping
    public List<Curso> listarCursos() throws SQLException {
        return cursoService.listar();
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable int id, @RequestBody Curso curso) throws SQLException {
        curso.setCodigo(id);
        cursoService.atualizar(curso);
        return curso;
    }

    @DeleteMapping("/{id}")
    public String deletarCurso(@PathVariable int id) throws SQLException {
        cursoService.excluir(id);
        return "Curso deletado com sucesso!";
    }

}
