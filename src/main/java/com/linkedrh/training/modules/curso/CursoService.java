package com.linkedrh.training.modules.curso;

import com.linkedrh.training.modules.curso.dtos.CreateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.ListCursoResponseDTO;
import com.linkedrh.training.modules.curso.entity.Curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired private CursoRepository cursoRepository;

    public int create(CreateCursoBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<ListCursoResponseDTO> list() throws Exception {
        List<Curso> cursos = this.cursoRepository.list();

        List<ListCursoResponseDTO> formatedCursos = new ArrayList<>();

        for (Curso curso : cursos) {
            ListCursoResponseDTO formatedCurso = new ListCursoResponseDTO();
            formatedCurso.buildFrom(curso);
            formatedCursos.add(formatedCurso);
        }

        return formatedCursos;
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
