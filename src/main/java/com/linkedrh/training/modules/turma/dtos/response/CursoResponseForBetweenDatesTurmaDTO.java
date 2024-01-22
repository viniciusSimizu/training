package com.linkedrh.training.modules.turma.dtos.response;

import com.linkedrh.training.modules.funcionario.dtos.response.CursoResponseForBetweenDatesFuncionarioDTO;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CursoResponseForBetweenDatesTurmaDTO that = (CursoResponseForBetweenDatesTurmaDTO) obj;

        if (this.codigo != that.codigo) return false;
        if (this.quantidadeParticipantes != that.quantidadeParticipantes) return false;
        if (!this.inicio.isEqual(that.inicio)) return false;
        if (!this.fim.isEqual(that.fim)) return false;
        if (!this.local.equals(that.local)) return false;

        if (this.funcionarios.size() != that.funcionarios.size()) return false;
        for (int i = 0; i < this.funcionarios.size(); i++) {
            if (!this.funcionarios.get(i).equals(that.funcionarios.get(i))) return false;
        }

        return true;
    }
}
