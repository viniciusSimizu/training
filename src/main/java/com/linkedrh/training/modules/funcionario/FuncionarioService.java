package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.modules.funcionario.dtos.CreateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.dtos.ListFuncionarioByTurmaResponseDTO;
import com.linkedrh.training.modules.funcionario.entity.Funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired private FuncionarioRepository cursoRepository;

    public int create(CreateFuncionarioBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }

    public List<ListFuncionarioByTurmaResponseDTO> listByTurma(int turmaId) throws Exception {
        List<Funcionario> funcionarios = this.cursoRepository.listByTurma(turmaId);
        List<ListFuncionarioByTurmaResponseDTO> formatedFuncionarios = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            ListFuncionarioByTurmaResponseDTO formatedFuncionario =
                    new ListFuncionarioByTurmaResponseDTO();
            formatedFuncionario.buildFrom(funcionario);
            formatedFuncionarios.add(formatedFuncionario);
        }

        return formatedFuncionarios;
    }

    public void updateAtivoField(int funcionarioId, boolean ativo) throws Exception {
        this.cursoRepository.updateAtivoField(funcionarioId, ativo);
    }
}
