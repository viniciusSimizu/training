package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.lib.enums.ErrorEnum;
import com.linkedrh.training.lib.helpers.ErrorHelper;
import com.linkedrh.training.lib.helpers.VerifyAuthorization;
import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.funcionario.dtos.request.CreateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.dtos.request.UpdateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.dtos.response.FuncionarioResponseList;
import com.linkedrh.training.modules.funcionario.dtos.response.FuncionarioResponseListByTurmaFuncionario;
import com.linkedrh.training.modules.funcionario.services.FuncionarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Object> create(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @RequestBody CreateFuncionarioBodyDTO body) {

        final String service = "criação de funcionário";
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> list(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @RequestParam(name = "ativo", required = false) Boolean ativo) {

        final String service = "listagem de funcionario por status";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        List<FuncionarioResponseList> funcionarios;

        try {
            funcionarios = this.service.list(ativo);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(funcionarios, HttpStatus.CREATED);
    }

    @GetMapping(path = "/turma/{turmaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listByTurma(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int turmaId) {

        final String service = "listagem de funcionario por turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        List<FuncionarioResponseListByTurmaFuncionario> funcionarios;

        try {
            funcionarios = this.service.listByTurma(turmaId);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(funcionarios, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{funcionarioId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int funcionarioId,
            @RequestBody UpdateFuncionarioBodyDTO body) {

        final String service = "atualizar funcionario";
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
            this.service.update(funcionarioId, body);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PutMapping(
            path = "/{funcionarioId}/ativo/{ativo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAtivoField(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
            @PathVariable int funcionarioId,
            @PathVariable boolean ativo) {

        final String service = "atualizar status do funcionario";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        try {
            this.service.updateAtivoField(funcionarioId, ativo);

        } catch (Exception except) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
