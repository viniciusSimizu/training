package com.linkedrh.training.modules.curso.dtos;

import com.linkedrh.training.lib.interfaces.Validated;

import java.util.ArrayList;
import java.util.List;

public class CreateCursoBodyDTO implements Validated {
    public String nome;
    public String descricao = "";
    public Integer duracao;

    private boolean valid = true;
    public List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid() {
        if (!this.valid) {
            return this.valid;
        }

        this.valid = this.isValidNome() && this.valid;
        this.valid = this.isValidDuracao() && this.valid;

        return this.valid;
    }

    private boolean isValidNome() {
        if (this.nome == null) {
            this.errors.add("'nome' deve ser preenchido");
            return false;
        }
        if (this.nome.length() == 0) {
            this.errors.add("'nome' não pode estar vazio");
            return false;
        }
        return true;
    }

    private boolean isValidDuracao() {
        if (this.duracao == null) {
            this.errors.add("'duracao' deve ser preenchido");
            return false;
        }
        if (this.duracao <= 0) {
            this.errors.add("'duracao' deve ser positivo");
            return false;
        }
        return true;
    }
}
