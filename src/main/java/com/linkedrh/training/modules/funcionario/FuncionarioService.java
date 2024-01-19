package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.modules.funcionario.dtos.CreateFuncionarioBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired private FuncionarioRepository cursoRepository;

    public int create(CreateFuncionarioBodyDTO body) throws Exception {
        return this.cursoRepository.create(body);
    }
}
