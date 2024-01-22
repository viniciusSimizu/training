package com.linkedrh.training.modules.turma.services;

import com.linkedrh.training.modules.turma.TurmaRepository;
import com.linkedrh.training.modules.turma.dtos.request.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.request.UpdateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.response.TurmaResponseForFindByCursoAndFuncionarioDTO;
import com.linkedrh.training.modules.turma.dtos.response.TurmaResponseForListByCursoTurmaDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    @Autowired private TurmaRepository cursoRepository;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<TurmaResponseForListByCursoTurmaDTO> listByCurso(int cursoId) throws Exception {
        List<Turma> turmas = this.cursoRepository.listByCurso(cursoId);
        return turmas.stream()
                .map(TurmaResponseForListByCursoTurmaDTO::new)
                .collect(Collectors.toList());
    }

    public TurmaResponseForFindByCursoAndFuncionarioDTO findByCursoAndFuncionario(
            int cursoId, int funcionarioId) throws Exception {
        Turma turma = this.cursoRepository.findByCursoAndFuncionario(cursoId, funcionarioId);
        if (turma != null) {
            return new TurmaResponseForFindByCursoAndFuncionarioDTO(turma);
        }
        return null;
    }

    public void update(int turmaId, UpdateTurmaBodyDTO body) throws Exception {
        this.cursoRepository.update(turmaId, body);
    }

    public void delete(int turmaId) throws Exception {
        this.cursoRepository.delete(turmaId);
    }
}
