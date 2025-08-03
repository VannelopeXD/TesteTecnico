package com.linkedrh.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedrh.crud.dao.CursoDao;
import com.linkedrh.crud.model.Curso;
import com.linkedrh.crud.model.Turma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TurmaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarUmaTurmaComSucesso() throws Exception {
        CursoDao cursoDao = new CursoDao();
        Curso cursoTeste = new Curso();
        cursoTeste.setNome("Curso Teste JUnit");
        cursoTeste.setDescricao("Curso criado só para teste");
        cursoTeste.setDuracao(60);

        cursoDao.salvarCurso(cursoTeste);
        List<Curso> cursos = cursoDao.listarCursos();
        Curso cursoSalvo = cursos.get(cursos.size() - 1);

        Turma novaTurma = new Turma();
        novaTurma.setInicio(LocalDate.now().plusDays(1));
        novaTurma.setFim(LocalDate.now().plusDays(5));
        novaTurma.setLocal("Sala 965");
        novaTurma.setLocal("Sala 966");
        novaTurma.setLocal("Sala 967");
        novaTurma.setCurso(cursoSalvo.getCodigo());


        mockMvc.perform(
                post("/turmas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaTurma))
        ).andExpect(status().isOk());
    }
}
