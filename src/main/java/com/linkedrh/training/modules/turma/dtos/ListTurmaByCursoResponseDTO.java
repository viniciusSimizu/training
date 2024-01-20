package com.linkedrh.training.modules.turma.dtos;

import com.linkedrh.training.lib.interfaces.BuildableDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import java.time.LocalDate;

public class ListTurmaByCursoResponseDTO implements BuildableDTO<Turma> {

    public int codigo, quantidadeParticipantes;
    public LocalDate inicio, fim;
    public String local;

    @Override
    public void buildFrom(Turma entity) {
        this.codigo = entity.codigo;
        this.quantidadeParticipantes = entity.quantidadeParticipantes;
        if (entity.inicio != null) {
            this.inicio = entity.inicio.toLocalDate();
        }
        if (entity.fim != null) {
            this.fim = entity.fim.toLocalDate();
        }
        this.local = entity.local;
    }
}
