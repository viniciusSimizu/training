package com.linkedrh.training.modules.curso;

import com.linkedrh.training.modules.curso.dtos.CreateCursoBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired private CursoRepository cursoRepository;

    public int create(CreateCursoBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public void delete(int cursoId, boolean force) throws Exception {
        if (force) {
            this.cursoRepository.delete(cursoId);
            return;
        }
        this.cursoRepository.updateAtivoField(cursoId, false);
    }
}
