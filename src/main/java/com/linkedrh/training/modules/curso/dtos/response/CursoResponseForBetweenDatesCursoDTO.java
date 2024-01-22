package com.linkedrh.training.modules.curso.dtos.response;

import com.linkedrh.training.modules.curso.entity.Curso;
import com.linkedrh.training.modules.turma.dtos.response.CursoResponseForBetweenDatesTurmaDTO;

import java.util.ArrayList;
import java.util.List;

public class CursoResponseForBetweenDatesCursoDTO {

    public int codigo, duracao, quantidadeTurmas;
    public String nome;
    public List<CursoResponseForBetweenDatesTurmaDTO> turmas = new ArrayList<>();

    public CursoResponseForBetweenDatesCursoDTO(Curso entity) {
        this.codigo = entity.codigo;
        this.duracao = entity.duracao;
        this.quantidadeTurmas = entity.quantidadeTurmas;
        this.nome = entity.nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CursoResponseForBetweenDatesCursoDTO that = (CursoResponseForBetweenDatesCursoDTO) obj;

        if (this.codigo != that.codigo) return false;
        if (this.duracao != that.duracao) return false;
        if (this.quantidadeTurmas != that.quantidadeTurmas) return false;
        if (!this.nome.equals(that.nome)) return false;

        if (this.turmas.size() != that.turmas.size()) return false;
        for (int i = 0; i < this.turmas.size(); i++) {
            if (!this.turmas.get(i).equals(that.turmas.get(i))) return false;
        }

        return true;
    }
}
