package com.linkedrh.training.modules.participante.services;

import com.linkedrh.training.modules.participante.ParticipanteRepository;
import com.linkedrh.training.modules.participante.dtos.request.CreateParticipanteBodyDTO;

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

    public void deleteByFuncionario(int funcionarioId) throws Exception {
        this.cursoRepository.deleteByFuncionario(funcionarioId);
    }
}
