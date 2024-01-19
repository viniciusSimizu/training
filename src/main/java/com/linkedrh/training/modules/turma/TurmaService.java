package com.linkedrh.training.modules.turma;

import com.linkedrh.training.modules.turma.dtos.CreateTurmaBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired private TurmaRepository cursoRepository;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }
}
