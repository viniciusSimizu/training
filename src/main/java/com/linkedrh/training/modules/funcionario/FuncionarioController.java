package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.funcionario.dtos.CreateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.dtos.ListFuncionarioByTurmaResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    final Logger log = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired private FuncionarioService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateFuncionarioBodyDTO body) {

        final String service = "criação de funcionário";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        final Map<String, Object> response = new HashMap<>();

        if (!body.isValid()) {
            response.put("requestBodyErrors", body.getErrors());
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        int codigo;
        try {
            codigo = this.service.create(body);

        } catch (Exception except) {
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("codigo", codigo);

        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/turma/{turmaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listByTurma(@PathVariable int turmaId) {

        final String service = "listagem de funcionario por turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        List<ListFuncionarioByTurmaResponseDTO> funcionarios;

        try {
            funcionarios = this.service.listByTurma(turmaId);

        } catch (Exception except) {
            final Map<String, Object> response = new HashMap<>();
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(funcionarios, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{funcionarioId}/ativo/{ativo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAtivoField(
            @PathVariable int funcionarioId, @PathVariable boolean ativo) {

        final String service = "atualizar status do funcionario";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        try {
            this.service.updateAtivoField(funcionarioId, ativo);

        } catch (Exception except) {
            final Map<String, Object> response = new HashMap<>();
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
