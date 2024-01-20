package com.linkedrh.training.modules.participante;

import com.linkedrh.training.modules.participante.dtos.CreateParticipanteBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteService {

    @Autowired private ParticipanteRepository cursoRepository;

    public int create(CreateParticipanteBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public void delete(int turmaId, int funcionarioId) throws Exception {
        this.cursoRepository.delete(turmaId, funcionarioId);
    }
}
