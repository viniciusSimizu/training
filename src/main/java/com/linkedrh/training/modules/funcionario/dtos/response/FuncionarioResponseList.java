package com.linkedrh.training.modules.funcionario.dtos.response;

import com.linkedrh.training.modules.funcionario.entity.Funcionario;

import java.time.LocalDate;

public class FuncionarioResponseList {

    public int codigo;
    public String nome, cpf, cargo;
    public LocalDate nascimento, admissao;
    public Boolean ativo;

    public FuncionarioResponseList(Funcionario entity) {
        this.codigo = entity.codigo;
        this.nome = entity.nome;
        this.cpf = entity.cpf;
        this.cargo = entity.cargo;
        if (entity.nascimento != null) {
            this.nascimento = entity.nascimento.toLocalDate();
        }
        if (entity.admissao != null) {
            this.admissao = entity.admissao.toLocalDate();
        }
        this.ativo = entity.ativo;
    }
}
