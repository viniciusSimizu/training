package com.linkedrh.training.modules.participante.dtos.request;

import com.linkedrh.training.lib.interfaces.Validated;
import com.linkedrh.training.modules.participante.helpers.ParticipanteHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateParticipanteBodyDTO implements Validated {
    public Integer funcionarioId;
    public Integer turmaId;

    private boolean valid = true;
    private List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = ParticipanteHelper.isValidFuncionarioId(funcionarioId, errors) && this.valid;
        this.valid = ParticipanteHelper.isValidTurmaId(turmaId, errors) && this.valid;

        return this.valid;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }
}
