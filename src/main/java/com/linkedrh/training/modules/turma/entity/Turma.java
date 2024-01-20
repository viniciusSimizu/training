package com.linkedrh.training.modules.turma.entity;

import java.time.LocalDate;

public class Turma {

    private int codigo, cursoId;
    private LocalDate inicio, fim;
    private String local;

    public int getCodigo() {
        return codigo;
    }

    public Turma setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public int getCursoId() {
        return cursoId;
    }

    public Turma setCursoId(int cursoId) {
        this.cursoId = cursoId;
        return this;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public Turma setInicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Turma setFim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public String getLocal() {
        return local;
    }

    public Turma setLocal(String local) {
        this.local = local;
        return this;
    }
}
