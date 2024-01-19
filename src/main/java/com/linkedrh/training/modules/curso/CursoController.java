package com.linkedrh.training.modules.curso;

import com.linkedrh.training.lib.log.LogMessageHandler;
import com.linkedrh.training.modules.curso.dtos.CreateCursoBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("curso")
public class CursoController {

    final Logger log = LoggerFactory.getLogger(CursoController.class);

    @Autowired private CursoService cursoService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateCursoBodyDTO body) {

        final String service = "criação de curso";
        LogMessageHandler.infoEndpointRegistry(service, this.log);

        final Map<String, Object> response = new HashMap<>();

        if (!body.isValid()) {
            response.put("requestBodyErrors", body.errors);
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        int codigo;
        try {
            codigo = this.cursoService.create(body);

        } catch (Exception except) {
            response.put("exception", except.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("codigo", codigo);

        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }
}
