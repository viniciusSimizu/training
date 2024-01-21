package com.linkedrh.training.modules.curso.dtos.response;

import com.linkedrh.training.modules.curso.entity.Curso;

import java.time.LocalDate;

public class CursoResponseForListCursoDTO {

    public String nome;
    public int duracao, quantidadeTurmas;
    public boolean ativo;
    public LocalDate dataInicioMaisProxima;

    public CursoResponseForListCursoDTO(Curso entity) {
        this.nome = entity.nome;
        this.duracao = entity.duracao;
        this.quantidadeTurmas = entity.quantidadeTurmas;
        this.ativo = entity.ativo;
        if (entity.dataInicioMaisProxima != null) {
            this.dataInicioMaisProxima = entity.dataInicioMaisProxima.toLocalDate();
        }
    }
}
