package com.linkedrh.crud;

import com.linkedrh.crud.dao.CursoDao;
import com.linkedrh.crud.model.Curso;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TesteCursoDao {
    @Test
    public void main() {
        CursoDao dao = new CursoDao();

        try {
            // 1. Criar um novo curso
            Curso novoCurso = new Curso();
            novoCurso.setNome("Java Básico");
            novoCurso.setDescricao("Introdução ao Java e lógica de programação");
            novoCurso.setDuracao(120);

            dao.salvarCurso(novoCurso);

            // 2. Listar todos os cursos
            List<Curso> cursos = dao.listarCursos();
            System.out.println("\n📋 Lista de cursos:");
            for (Curso c : cursos) {
                System.out.println(c.getCodigo() + " - " + c.getNome() + " (" + c.getDuracao() + "min)");
            }

            // 3. Atualizar o primeiro curso encontrado (se existir)
            if (!cursos.isEmpty()) {
                Curso cursoAtualizar = cursos.get(0);
                cursoAtualizar.setNome("Java Básico - Atualizado");
                dao.AlterarCurso(cursoAtualizar);
            }

            // 4. Excluir o último curso encontrado (apenas para testar)
            if (!cursos.isEmpty()) {
                Curso cursoExcluir = cursos.get(cursos.size() - 1);
                dao.excluirCurso(cursoExcluir);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
