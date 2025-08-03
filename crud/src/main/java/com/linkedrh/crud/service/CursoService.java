package com.linkedrh.crud.service;
import com.linkedrh.crud.dao.CursoDao;
import com.linkedrh.crud.model.Curso;
import java.sql.SQLException;
import java.util.List;

public class CursoService {

    private CursoDao cursoDao = new CursoDao();

    //Salvar o curso.
    public void salvar(Curso curso) throws SQLException {
        cursoDao.salvarCurso(curso);
    }

    //Listando todos os cursos.
    public List<Curso> listar() throws SQLException {
        return cursoDao.listarCursos();
    }

    //Atualizando os cursos.
    public void atualizar(Curso curso) throws SQLException {
        cursoDao.AlterarCurso(curso);
    }

    //exclusão dos cursos.
    public void excluir(int id) throws SQLException {
        Curso curso = new Curso();
        curso.setCodigo(id);
        cursoDao.excluirCurso(curso);
    }
}
