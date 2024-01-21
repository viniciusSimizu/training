package com.linkedrh.training.modules.turma.dtos.response;

import com.linkedrh.training.modules.funcionario.dtos.CursoResponseForBetweenDatesFuncionarioDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import java.time.LocalDate;
import java.util.List;

public class CursoResponseForBetweenDatesTurmaDTO {

    public int codigo, quantidadeParticipantes;
    public LocalDate inicio, fim;
    public String local;
    public List<CursoResponseForBetweenDatesFuncionarioDTO> funcionarios;

    public CursoResponseForBetweenDatesTurmaDTO(Turma entity) {
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
