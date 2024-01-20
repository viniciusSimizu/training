package com.linkedrh.training.modules.curso.dtos;

import com.linkedrh.training.lib.interfaces.Validated;
import com.linkedrh.training.modules.curso.helpers.CursoValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateCursoBodyDTO implements Validated {
    public String nome, descricao = "";
    public Integer duracao;

    private boolean valid = true;
    private List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = CursoValidationHelper.isValidNome(nome, errors) && this.valid;
        this.valid = CursoValidationHelper.isValidDuracao(duracao, errors) && this.valid;

        return this.valid;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }
}
