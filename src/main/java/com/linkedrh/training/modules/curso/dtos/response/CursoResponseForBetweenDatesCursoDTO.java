package com.linkedrh.training.modules.curso.dtos.response;

import com.linkedrh.training.modules.curso.entity.Curso;
import com.linkedrh.training.modules.turma.dtos.response.CursoResponseForBetweenDatesTurmaDTO;

import java.util.List;

public class CursoResponseForBetweenDatesCursoDTO {

    public int codigo, duracao, quantidadeTurmas;
    public String nome;
    public List<CursoResponseForBetweenDatesTurmaDTO> turmas;

    public CursoResponseForBetweenDatesCursoDTO(Curso entity) {
        this.codigo = entity.codigo;
        this.duracao = entity.duracao;
        this.quantidadeTurmas = entity.duracao;
        this.nome = entity.nome;
    }
}
