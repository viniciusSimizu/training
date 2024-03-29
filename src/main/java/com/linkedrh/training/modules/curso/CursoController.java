package com.linkedrh.training.modules.curso;

import com.linkedrh.training.lib.enums.ErrorEnum;
import com.linkedrh.training.lib.helpers.ErrorHelper;
import com.linkedrh.training.lib.helpers.VerifyAuthorization;
import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.curso.dtos.request.CreateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.request.UpdateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.response.CursoResponseForBetweenDatesCursoDTO;
import com.linkedrh.training.modules.curso.dtos.response.CursoResponseForListCursoDTO;
import com.linkedrh.training.modules.curso.services.CursoListBetweenDatesService;
import com.linkedrh.training.modules.curso.services.CursoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("curso")
public class CursoController {

    final Logger log = LoggerFactory.getLogger(CursoController.class);

    @Autowired private CursoService service;
    @Autowired private CursoListBetweenDatesService listBetweenDatesService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token,
            @RequestBody CreateCursoBodyDTO body) {

        final String service = "criação de curso";
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
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {

        final String service = "listagem de cursos";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        List<CursoResponseForListCursoDTO> cursos;

        try {
            cursos = this.service.list();

        } catch (Exception e) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(cursos, HttpStatus.OK);
    }

    @GetMapping(path = "/date/range/{inicio}/{fim}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listBetweenDates(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token,
            @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate inicio,
            @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate fim) {

        final String service = "listagem de cursos entre datas";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        List<CursoResponseForBetweenDatesCursoDTO> cursos;

        try {
            cursos = this.listBetweenDatesService.list(inicio, fim);

        } catch (Exception e) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(cursos, HttpStatus.OK);
    }

    @PutMapping(
            path = "/{cursoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token,
            @PathVariable int cursoId,
            @RequestBody UpdateCursoBodyDTO body) {

        final String service = "atualizar curso";
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
            this.service.update(cursoId, body);

        } catch (Exception e) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{cursoId}")
    public ResponseEntity<Object> delete(
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token,
            @PathVariable int cursoId,
            @RequestParam(name = "force", required = false, defaultValue = "false") Boolean force) {

        final String service = "deleção de curso";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!VerifyAuthorization.verifyToken(token)) {
            Object response = ErrorHelper.createMessage(ErrorEnum.AUTHORIZATION, null);
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }

        try {
            this.service.delete(cursoId, force);

        } catch (Exception e) {
            Object response = ErrorHelper.createMessage(ErrorEnum.EXCEPTION, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
