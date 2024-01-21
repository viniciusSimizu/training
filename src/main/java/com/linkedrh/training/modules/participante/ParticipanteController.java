package com.linkedrh.training.modules.participante;

import com.linkedrh.training.lib.enums.ErrorEnum;
import com.linkedrh.training.lib.helpers.ErrorHelper;
import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.participante.dtos.request.CreateParticipanteBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("turma/participante")
public class ParticipanteController {

    final Logger log = LoggerFactory.getLogger(ParticipanteController.class);

    @Autowired private ParticipanteService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateParticipanteBodyDTO body) {

        final String service = "criação de participante";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!body.isValid()) {
            Object response = ErrorHelper.createMessage(ErrorEnum.VALIDATION, body.getErrors());
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        int codigo;
        try {
            codigo = this.service.create(body);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String, Integer> response = new HashMap<>();
        response.put("codigo", codigo);
        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/turma/{turmaId}/funcionario/{funcionarioId}")
    public ResponseEntity<Object> delete(
            @PathVariable int turmaId, @PathVariable int funcionarioId) {

        final String service = "deletar participante";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        try {
            this.service.delete(turmaId, funcionarioId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/funcionario/{funcionarioId}")
    public ResponseEntity<Object> delete(@PathVariable int funcionarioId) {

        final String service = "deletar participantes por funcionário";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        try {
            this.service.deleteByFuncionario(funcionarioId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
