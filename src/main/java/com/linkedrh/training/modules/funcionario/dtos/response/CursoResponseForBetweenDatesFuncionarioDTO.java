package com.linkedrh.training.modules.funcionario.dtos.response;

import com.linkedrh.training.modules.funcionario.entity.Funcionario;

public class CursoResponseForBetweenDatesFuncionarioDTO {

    public int codigo;
    public String nome;

    public CursoResponseForBetweenDatesFuncionarioDTO(Funcionario entity) {
        this.codigo = entity.codigo;
        this.nome = entity.nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CursoResponseForBetweenDatesFuncionarioDTO that =
                (CursoResponseForBetweenDatesFuncionarioDTO) obj;

        if (this.codigo != that.codigo) return false;
        if (!this.nome.equals(that.nome)) return false;

        return true;
    }
}
