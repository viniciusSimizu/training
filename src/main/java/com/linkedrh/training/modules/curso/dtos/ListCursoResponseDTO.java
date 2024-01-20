package com.linkedrh.training.modules.curso.dtos;

import com.linkedrh.training.lib.interfaces.BuildableDTO;
import com.linkedrh.training.modules.curso.entity.Curso;

import java.time.LocalDate;

public class ListCursoResponseDTO implements BuildableDTO<Curso> {

    public String nome;
    public int duracao, quantidadeTurmas;
    public boolean ativo;
    public LocalDate dataInicioMaisProxima;

    @Override
    public void buildFrom(Curso entity) {
        this.nome = entity.nome;
        this.duracao = entity.duracao;
        this.quantidadeTurmas = entity.quantidadeTurmas;
        this.ativo = entity.ativo;
        if (entity.dataInicioMaisProxima != null) {
            this.dataInicioMaisProxima = entity.dataInicioMaisProxima.toLocalDate();
        }
    }
}
