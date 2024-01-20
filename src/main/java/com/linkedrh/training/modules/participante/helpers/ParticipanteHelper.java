package com.linkedrh.training.modules.participante.helpers;

import java.util.List;

public class ParticipanteHelper {

    public static boolean isValidFuncionarioId(Integer funcionarioId, List<String> errors) {
        if (funcionarioId == null) {
            errors.add("funcionarioId deve ser preenchido");
            return false;
        }
        return true;
    }

    public static boolean isValidTurmaId(Integer turmaId, List<String> errors) {
        if (turmaId == null) {
            errors.add("turmaId deve ser preenchido");
            return false;
        }
        return true;
    }
}
