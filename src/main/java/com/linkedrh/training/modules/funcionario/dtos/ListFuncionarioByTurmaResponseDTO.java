package com.linkedrh.training.modules.funcionario.dtos;

import com.linkedrh.training.lib.interfaces.BuildableDTO;
import com.linkedrh.training.modules.funcionario.entity.Funcionario;

import java.time.LocalDate;

public class ListFuncionarioByTurmaResponseDTO implements BuildableDTO<Funcionario> {

    public int codigo;
    public String nome, cpf, cargo;
    public LocalDate nascimento, admissao;
    public Boolean ativo;

    @Override
    public void buildFrom(Funcionario entity) {
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