package com.linkedrh.training.modules.turma;

import com.linkedrh.training.modules.participante.ParticipanteRepository;
import com.linkedrh.training.modules.turma.dtos.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.TurmaByCursoResponseDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    @Autowired private TurmaRepository cursoRepository;
    @Autowired private ParticipanteRepository participanteRepository;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<TurmaByCursoResponseDTO> listByCurso(int cursoId) throws Exception {
        List<Turma> turmas = this.cursoRepository.listByCurso(cursoId);

        List<TurmaByCursoResponseDTO> response = new ArrayList<>(turmas.size());

        for (Turma turma : turmas) {
            int quantidadeParticipantes =
                    this.participanteRepository.countTurmaParticipantes(turma.getCodigo());

            TurmaByCursoResponseDTO item = new TurmaByCursoResponseDTO();
            item.codigo = turma.getCodigo();
            item.quantidadeParticipantes = quantidadeParticipantes;
            item.inicio = turma.getInicio();
            item.fim = turma.getFim();
            item.local = turma.getLocal();

            response.add(item);
        }

        return response;
    }
}
