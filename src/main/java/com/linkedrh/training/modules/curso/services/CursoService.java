package com.linkedrh.training.modules.curso.services;

import com.linkedrh.training.modules.curso.CursoRepository;
import com.linkedrh.training.modules.curso.dtos.request.CreateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.request.UpdateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.response.CursoResponseForListCursoDTO;
import com.linkedrh.training.modules.curso.entity.Curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired private CursoRepository cursoRepository;

    public int create(CreateCursoBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<CursoResponseForListCursoDTO> list() throws Exception {
        List<Curso> cursos = this.cursoRepository.list();
        return cursos.stream().map(CursoResponseForListCursoDTO::new).collect(Collectors.toList());
    }

    public void update(int cursoId, UpdateCursoBodyDTO body) throws Exception {
        this.cursoRepository.update(cursoId, body);
    }

    public void delete(int cursoId, boolean force) throws Exception {
        if (force) {
            this.cursoRepository.delete(cursoId);
            return;
        }

        if (this.cursoRepository.hasTurmas(cursoId)) {
            this.cursoRepository.updateAtivoField(cursoId, false);
            return;
        }

        this.cursoRepository.delete(cursoId);
    }
}
