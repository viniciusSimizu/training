package com.linkedrh.training.modules.participante.dtos;

import com.linkedrh.training.lib.interfaces.Validated;

import java.util.ArrayList;
import java.util.List;

public class CreateParticipanteBodyDTO implements Validated {
    public Integer funcionarioId;
    public Integer turmaId;

    private boolean valid = true;
    public List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = this.isValidFuncionarioId() && this.valid;
        this.valid = this.isValidTurmaId() && this.valid;

        return this.valid;
    }

    private boolean isValidFuncionarioId() {
        if (this.funcionarioId == null) {
            return false;
        }
        return true;
    }

    private boolean isValidTurmaId() {
        if (this.turmaId == null) {
            return false;
        }
        return true;
    }
}
