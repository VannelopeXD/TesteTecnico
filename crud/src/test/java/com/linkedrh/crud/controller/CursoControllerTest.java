package com.linkedrh.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedrh.crud.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveFazerCrudDeCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNome("Curso de Teste 1");
        curso.setNome("Curso de Teste 2");
        curso.setNome("Curso de Teste 3");
        curso.setNome("Curso de Teste 4");
        curso.setNome("Curso de Teste 5");
        curso.setNome("Curso de Teste 6");
        curso.setDescricao("Curso para validar CRUD completo");
        curso.setDuracao(60);

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk());


        curso.setNome("Curso Atualizado");
        mockMvc.perform(put("/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/cursos/1"))
                .andExpect(status().isOk());
    }
}
