package com.linkedrh.training.modules.funcionario.dtos;

import com.linkedrh.training.lib.interfaces.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CreateFuncionarioBodyDTO implements Validated {
    public String nome, cpf, cargo;
    public LocalDate nascimento, admissao;

    private boolean valid = true;
    public List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = this.isValidNome() && this.valid;
        this.valid = this.isValidCpf() && this.valid;
        this.valid = this.isValidNascimento() && this.valid;
        this.valid = this.isValidCargo() && this.valid;
        this.valid = this.isValidAdmissao() && this.valid;

        return this.valid;
    }

    private boolean isValidNome() {
        if (this.nome == null) {
            this.errors.add("nome deve ser preenchido");
            return false;
        }
        if (this.nome.length() == 0) {
            this.errors.add("nome não pode estar vazio");
            return false;
        }
        return true;
    }

    private boolean isValidCpf() {
        if (this.cpf == null) {
            this.errors.add("cpf deve ser preenchido");
            return false;
        }

        Matcher cpfMatcher = Pattern.compile("\\d").matcher(this.cpf);
        String newCpf = cpfMatcher.results().map(MatchResult::group).collect(Collectors.joining());

        if (newCpf.length() != 11) {
            this.errors.add("cpf deve conter onze (11) números");
            return false;
        }
        this.cpf = newCpf;

        return true;
    }

    private boolean isValidNascimento() {
        if (this.nascimento == null) {
            this.errors.add("dataDeNascimento deve ser preenchido");
            return false;
        }

        if (this.nascimento.isAfter(LocalDate.now())) {
            this.errors.add("dataDeNascimento não pode estar no futuro");
            return false;
        }
        return true;
    }

    private boolean isValidCargo() {
        if (this.cargo == null) {
            this.errors.add("cargo deve ser preenchido");
            return false;
        }
        if (this.cargo.length() == 0) {
            this.errors.add("cargo não pode estar vazio");
            return false;
        }
        return true;
    }

    private boolean isValidAdmissao() {
        if (this.admissao == null) {
            this.errors.add("admissao deve ser preenchido");
            return false;
        }
        if (this.nascimento != null && this.admissao.isBefore(this.nascimento)) {
            this.errors.add("admissao não pode menor que a dataDeNascimento");
            return false;
        }
        return true;
    }
}
