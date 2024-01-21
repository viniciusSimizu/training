package com.linkedrh.training.modules.turma;

import com.linkedrh.training.modules.turma.dtos.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.ListTurmaByCursoResponseDTO;
import com.linkedrh.training.modules.turma.dtos.UpdateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    @Autowired private TurmaRepository cursoRepository;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<ListTurmaByCursoResponseDTO> listByCurso(int cursoId) throws Exception {
        List<Turma> turmas = this.cursoRepository.listByCurso(cursoId);

        List<ListTurmaByCursoResponseDTO> formatedTurmas = new ArrayList<>(turmas.size());

        for (Turma turma : turmas) {
            ListTurmaByCursoResponseDTO formatedTurma = new ListTurmaByCursoResponseDTO();
            formatedTurma.buildFrom(turma);

            formatedTurmas.add(formatedTurma);
        }

        return formatedTurmas;
    }

    public void update(int turmaId, UpdateTurmaBodyDTO body) throws Exception {
        this.cursoRepository.update(turmaId, body);
    }

    public void delete(int turmaId) throws Exception {
        this.cursoRepository.delete(turmaId);
    }
}
