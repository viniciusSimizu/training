package com.linkedrh.training.modules.curso.dtos.request;

import com.linkedrh.training.lib.interfaces.Validated;
import com.linkedrh.training.modules.curso.helpers.CursoValidationHelper;

import java.util.List;

public class UpdateCursoBodyDTO implements Validated {

    public String nome, descricao = "";
    public Integer duracao;

    private boolean valid = true;
    private List<String> errors;

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
