package com.linkedrh.crud.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Turma {

    private int codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String local;
    private Integer curso;
    private List<Integer> participantes = new ArrayList<>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public List<Integer> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Integer> participantes) {
        this.participantes = participantes;
    }
}
