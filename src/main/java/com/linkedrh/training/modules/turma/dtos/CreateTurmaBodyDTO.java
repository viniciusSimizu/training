package com.linkedrh.training.modules.turma.dtos;

import com.linkedrh.training.lib.interfaces.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateTurmaBodyDTO implements Validated {
    public LocalDate inicio, fim;
    public String local;
    public Integer cursoId;

    private boolean valid = true;
    public List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = this.isValidInicio() && this.valid;
        this.valid = this.isValidFim() && this.valid;
        this.valid = this.isValidLocal() && this.valid;
        this.valid = this.isValidCursoId() && this.valid;

        return this.valid;
    }

    private boolean isValidInicio() {
        if (this.inicio == null) {
            this.errors.add("inicio deve ser preenchido");
            return false;
        }
        if (this.inicio.isBefore(LocalDate.now())) {
            this.errors.add("inicio não pode ser agendado no passado");
            return false;
        }

        return true;
    }

    private boolean isValidFim() {
        if (this.fim == null) {
            this.errors.add("fim deve ser preenchido");
            return false;
        }
        if (this.inicio != null && this.fim.isBefore(this.inicio)) {
            this.errors.add("fim deve acontecer depois do inicio");
            return false;
        }

        return true;
    }

    private boolean isValidLocal() {
        if (this.local == null) {
            this.errors.add("local deve ser preenchido");
            return false;
        }

        return true;
    }

    private boolean isValidCursoId() {
        if (this.cursoId == null) {
            this.errors.add("cursoId deve ser preenchido");
            return false;
        }

        return true;
    }
}
