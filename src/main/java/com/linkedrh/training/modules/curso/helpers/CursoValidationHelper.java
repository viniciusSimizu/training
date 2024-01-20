package com.linkedrh.training.modules.curso.helpers;

import java.util.List;

public class CursoValidationHelper {

    public static boolean isValidNome(String nome, List<String> errors) {
        if (nome == null) {
            errors.add("nome deve ser preenchido");
            return false;
        }
        if (nome.length() == 0) {
            errors.add("nome não pode estar vazio");
            return false;
        }
        return true;
    }

    public static boolean isValidDuracao(Integer duracao, List<String> errors) {
        if (duracao == null) {
            errors.add("duracao deve ser preenchido");
            return false;
        }
        if (duracao <= 0) {
            errors.add("duracao deve ser positivo");
            return false;
        }
        return true;
    }
}
