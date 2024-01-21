package com.linkedrh.training.modules.funcionario.dtos.response;

import com.linkedrh.training.modules.funcionario.entity.Funcionario;

public class CursoResponseForBetweenDatesFuncionarioDTO {

    public int codigo;
    public String nome;

    public CursoResponseForBetweenDatesFuncionarioDTO(Funcionario entity) {
        this.codigo = entity.codigo;
        this.nome = entity.nome;
    }
}
