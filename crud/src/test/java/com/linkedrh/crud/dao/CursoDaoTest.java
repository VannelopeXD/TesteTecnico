package com.linkedrh.crud.dao;

import com.linkedrh.crud.model.Curso;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CursoDaoTest {

    private static CursoDao cursoDao;

    @BeforeAll
    static void setup() {
        cursoDao = new CursoDao();
    }

    @Test
    void deveSalvarListarEExcluirCurso() throws SQLException {
        // 1. Criar curso
        Curso curso = new Curso();
        curso.setNome("JUnit Test");
        curso.setDescricao("Curso para teste automatizado");
        curso.setDuracao(60);

        cursoDao.salvarCurso(curso);

        // 2. Listar curso
        List<Curso> cursos = cursoDao.listarCursos();
        assertFalse(cursos.isEmpty(), "A lista de cursos não deveria estar vazia");

        Curso cursoInserido = cursos.get(cursos.size() - 1);
        System.out.println("Curso inserido: " + cursoInserido.getNome());

        // 3. Alterar curso
        cursoInserido.setNome("JUnit Test Atualizado");
        cursoDao.AlterarCurso(cursoInserido);

        // 4. Listar novamente
        List<Curso> cursosAtualizados = cursoDao.listarCursos();
        Curso cursoAtualizado = cursosAtualizados
                .stream()
                .filter(c -> c.getCodigo().equals(cursoInserido.getCodigo()))
                .findFirst()
                .orElseThrow();
        assertEquals("JUnit Test Atualizado", cursoAtualizado.getNome());

        // 5. Excluir curso
        cursoDao.excluirCurso(cursoAtualizado);

        // 6. Verificar se existe
        List<Curso> cursosFinais = cursoDao.listarCursos();
        boolean existe = cursosFinais.stream()
                .anyMatch(c -> c.getCodigo().equals(cursoAtualizado.getCodigo()));
        assertFalse(existe, "O curso deveria ter sido excluído!");
    }
}
