package com.linkedrh.training.modules.turma.dtos;

import com.linkedrh.training.lib.interfaces.Validated;
import com.linkedrh.training.modules.turma.helpers.TurmaHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateTurmaBodyDTO implements Validated {
    public LocalDate inicio, fim;
    public String local;
    public Integer cursoId;

    private boolean valid = true;
    private List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = TurmaHelper.isValidInicio(inicio, errors) && this.valid;
        this.valid = TurmaHelper.isValidFim(fim, inicio, errors) && this.valid;
        this.valid = TurmaHelper.isValidLocal(local, errors) && this.valid;
        this.valid = TurmaHelper.isValidCursoId(cursoId, errors) && this.valid;

        return this.valid;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }
}
