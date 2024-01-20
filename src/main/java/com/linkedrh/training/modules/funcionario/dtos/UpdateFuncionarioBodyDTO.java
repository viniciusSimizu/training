package com.linkedrh.training.modules.funcionario.dtos;

import com.linkedrh.training.lib.interfaces.Validated;
import com.linkedrh.training.modules.funcionario.helpers.FuncionarioHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UpdateFuncionarioBodyDTO implements Validated {
    public String nome, cpf, cargo;
    public LocalDate nascimento, admissao;

    private boolean valid = true;
    private List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.formatCpf();

        this.valid = FuncionarioHelper.isValidNome(nome, errors) && this.valid;
        this.valid = FuncionarioHelper.isValidCpf(cpf, errors) && this.valid;
        this.valid = FuncionarioHelper.isValidNascimento(nascimento, errors) && this.valid;
        this.valid = FuncionarioHelper.isValidCargo(cargo, errors) && this.valid;
        this.valid = FuncionarioHelper.isValidAdmissao(admissao, nascimento, errors) && this.valid;

        return this.valid;
    }

    private void formatCpf() {
        if (this.cpf == null) {
            return;
        }

        Matcher cpfMatcher = Pattern.compile("\\d").matcher(this.cpf);
        String newCpf = cpfMatcher.results().map(MatchResult::group).collect(Collectors.joining());
        this.cpf = newCpf;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }
}
