package com.linkedrh.training.modules.turma;

import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.turma.dtos.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.ListTurmaByCursoResponseDTO;
import com.linkedrh.training.modules.turma.dtos.UpdateTurmaBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("turma")
public class TurmaController {

    final Logger log = LoggerFactory.getLogger(TurmaController.class);

    @Autowired private TurmaService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateTurmaBodyDTO body) {

        final String service = "criação de turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        Map<String, Object> response = new HashMap<>();

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

    @GetMapping(path = "/curso/{cursoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listByCurso(@PathVariable int cursoId) {

        final String service = "listagem de turmas por curso";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        List<ListTurmaByCursoResponseDTO> turmas;

        try {
            turmas = this.service.listByCurso(cursoId);

        } catch (Exception except) {
            Map<String, Object> response = new HashMap<>();
            response.put("exception", except.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(turmas, HttpStatus.OK);
    }

    @PutMapping(path = "/{turmaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @PathVariable int turmaId, @RequestBody UpdateTurmaBodyDTO body) {

        final String service = "atualização da turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        if (!body.isValid()) {
            Map<String, Object> response = new HashMap<>();
            response.put("requestBodyErrors", body.getErrors());
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            this.service.update(turmaId, body);

        } catch (Exception except) {
            Map<String, Object> response = new HashMap<>();
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{turmaId}")
    public ResponseEntity<Object> create(@PathVariable int turmaId) {

        final String service = "deletar turma";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        try {
            this.service.delete(turmaId);

        } catch (Exception except) {
            Map<String, Object> response = new HashMap<>();
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
