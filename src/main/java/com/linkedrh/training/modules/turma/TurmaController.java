package com.linkedrh.training.modules.turma;

import com.linkedrh.training.lib.enums.ErrorEnum;
import com.linkedrh.training.lib.helpers.ErrorHelper;
import com.linkedrh.training.lib.helpers.VerifyAuthorization;
import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.turma.dtos.request.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.request.UpdateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.response.TurmaResponseForFindByCursoAndFuncionarioDTO;
import com.linkedrh.training.modules.turma.dtos.response.TurmaResponseForListByCursoTurmaDTO;
import com.linkedrh.training.modules.turma.services.TurmaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("turma")
public class TurmaController {

    final Logger log = LoggerFactory.getLogger(TurmaController.class);

    @Autowired private TurmaService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @RequestBody CreateTurmaBodyDTO body) {

        final String service = "criação de turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

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

    @GetMapping(path = "/curso/{cursoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listByCurso(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int cursoId) {

        final String service = "listagem de turmas por curso";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        List<TurmaResponseForListByCursoTurmaDTO> turmas;

        try {
            turmas = this.service.listByCurso(cursoId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(turmas, HttpStatus.OK);
    }

    @GetMapping(
            path = "/curso/{cursoId}/funcionario/{funcionarioId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByCursoAndFuncionario(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int cursoId,
            @PathVariable int funcionarioId) {

        final String service = "buscar turma por curso e funcionário";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        TurmaResponseForFindByCursoAndFuncionarioDTO turma;

        try {
            turma = this.service.findByCursoAndFuncionario(cursoId, funcionarioId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (turma == null) {
            return new ResponseEntity<Object>(turma, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(turma, HttpStatus.OK);
    }

    @PutMapping(path = "/{turmaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int turmaId,
            @RequestBody UpdateTurmaBodyDTO body) {

        final String service = "atualização da turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        if (!body.isValid()) {
            Object response = ErrorHelper.createMessage(ErrorEnum.VALIDATION, body.getErrors());
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            this.service.update(turmaId, body);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{turmaId}")
    public ResponseEntity<Object> create(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int turmaId) {

        final String service = "deletar turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        try {
            this.service.delete(turmaId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
