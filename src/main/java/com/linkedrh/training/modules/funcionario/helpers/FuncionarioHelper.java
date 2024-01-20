package com.linkedrh.training.modules.funcionario.helpers;

import java.time.LocalDate;
import java.util.List;

public class FuncionarioHelper {

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

    public static boolean isValidCpf(String cpf, List<String> errors) {
        if (cpf == null) {
            errors.add("cpf deve ser preenchido");
            return false;
        }

        if (cpf.length() != 11) {
            errors.add("cpf deve conter onze (11) números");
            return false;
        }

        return true;
    }

    public static boolean isValidNascimento(LocalDate nascimento, List<String> errors) {
        if (nascimento == null) {
            errors.add("dataDeNascimento deve ser preenchido");
            return false;
        }

        if (nascimento.isAfter(LocalDate.now())) {
            errors.add("dataDeNascimento não pode estar no futuro");
            return false;
        }
        return true;
    }

    public static boolean isValidCargo(String cargo, List<String> errors) {
        if (cargo == null) {
            errors.add("cargo deve ser preenchido");
            return false;
        }
        if (cargo.length() == 0) {
            errors.add("cargo não pode estar vazio");
            return false;
        }
        return true;
    }

    public static boolean isValidAdmissao(
            LocalDate admissao, LocalDate nascimento, List<String> errors) {
        if (admissao == null) {
            errors.add("admissao deve ser preenchido");
            return false;
        }
        if (nascimento != null && admissao.isBefore(nascimento)) {
            errors.add("admissao não pode menor que a dataDeNascimento");
            return false;
        }
        return true;
    }
}
