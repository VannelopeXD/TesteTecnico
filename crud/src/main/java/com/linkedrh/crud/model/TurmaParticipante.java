package com.linkedrh.crud.model;

public class TurmaParticipante {
    private Integer codigo;
    private Integer Turma;
    private Integer funcionario;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getTurma() {
        return Turma;
    }

    public void setTurma(Integer turma) {
        Turma = turma;
    }

    public Integer getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }
}
