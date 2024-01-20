package com.linkedrh.training.modules.turma.helpers;

import java.time.LocalDate;
import java.util.List;

public class TurmaHelper {

    public static boolean isValidInicio(LocalDate inicio, List<String> errors) {
        if (inicio == null) {
            errors.add("inicio deve ser preenchido");
            return false;
        }
        if (inicio.isBefore(LocalDate.now())) {
            errors.add("inicio não pode ser agendado no passado");
            return false;
        }

        return true;
    }

    public static boolean isValidFim(LocalDate fim, LocalDate inicio, List<String> errors) {
        if (fim == null) {
            errors.add("fim deve ser preenchido");
            return false;
        }
        if (inicio != null && fim.isBefore(inicio)) {
            errors.add("fim deve acontecer depois do inicio");
            return false;
        }

        return true;
    }

    public static boolean isValidLocal(String local, List<String> errors) {
        if (local == null) {
            errors.add("local deve ser preenchido");
            return false;
        }

        return true;
    }

    public static boolean isValidCursoId(Integer cursoId, List<String> errors) {
        if (cursoId == null) {
            errors.add("cursoId deve ser preenchido");
            return false;
        }

        return true;
    }
}
