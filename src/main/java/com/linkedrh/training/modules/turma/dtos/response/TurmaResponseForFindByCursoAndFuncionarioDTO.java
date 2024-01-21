package com.linkedrh.training.modules.turma.dtos.response;

import com.linkedrh.training.modules.turma.entity.Turma;

import java.time.LocalDate;

public class TurmaResponseForFindByCursoAndFuncionarioDTO {

    public int codigo;
    public LocalDate inicio, fim;
    public String local;

    public TurmaResponseForFindByCursoAndFuncionarioDTO(Turma entity) {
        this.codigo = entity.codigo;
        if (entity.inicio != null) {
            this.inicio = entity.inicio.toLocalDate();
        }
        if (entity.fim != null) {
            this.fim = entity.fim.toLocalDate();
        }
        this.local = entity.local;
    }
}
