package com.linkedrh.training.modules.curso;

import com.linkedrh.training.modules.curso.dtos.request.CreateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.request.UpdateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.response.CursoResponseForBetweenDatesCursoDTO;
import com.linkedrh.training.modules.curso.dtos.response.CursoResponseForListCursoDTO;
import com.linkedrh.training.modules.curso.entity.Curso;
import com.linkedrh.training.modules.funcionario.FuncionarioRepository;
import com.linkedrh.training.modules.funcionario.dtos.response.CursoResponseForBetweenDatesFuncionarioDTO;
import com.linkedrh.training.modules.funcionario.entity.Funcionario;
import com.linkedrh.training.modules.turma.TurmaRepository;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired private CursoRepository cursoRepository;
    @Autowired private TurmaRepository turmaRepository;
    @Autowired private FuncionarioRepository funcionarioRepository;

    public int create(CreateCursoBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<CursoResponseForListCursoDTO> list() throws Exception {
        List<Curso> cursos = this.cursoRepository.list();
        return cursos.stream().map(CursoResponseForListCursoDTO::new).collect(Collectors.toList());
    }

    public List<CursoResponseForBetweenDatesCursoDTO> listBetweenDates(
            LocalDate inicio, LocalDate fim) throws Exception {
        List<Curso> cursos = this.cursoRepository.list();
        List<CursoResponseForBetweenDatesCursoDTO> formatedCursos = new ArrayList<>();

        for (Curso curso : cursos) {
            List<Turma> turmas = this.turmaRepository.listByCurso(curso.codigo);
            List<ListTurmaBetweenDatesResponseDTO> formatedTurmas = new ArrayList<>();

            for (Turma turma : turmas) {
                List<Funcionario> funcionarios =
                        this.funcionarioRepository.listByTurma(turma.codigo);
                List<CursoResponseForBetweenDatesFuncionarioDTO> formatedFuncionarios =
                        new ArrayList<>();

                for (Funcionario funcionario : funcionarios) {
                    CursoResponseForBetweenDatesFuncionarioDTO formatedFuncionario =
                            new CursoResponseForBetweenDatesFuncionarioDTO();
                    formatedFuncionario.buildFrom(funcionario);
                    formatedFuncionarios.add(formatedFuncionario);
                }

                ListTurmaBetweenDatesResponseDTO formatedTurma =
                        new ListTurmaBetweenDatesResponseDTO();
                formatedTurma.buildFrom(turma);
                formatedTurma.funcionarios = formatedFuncionarios;
                formatedTurmas.add(formatedTurma);
            }

            CursoResponseForBetweenDatesCursoDTO formatedCurso =
                    new CursoResponseForBetweenDatesCursoDTO();
            formatedCurso.buildFrom(curso);
            formatedCurso.turmas = formatedTurmas;
            formatedCursos.add(formatedCurso);
        }

        return formatedCursos;
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
