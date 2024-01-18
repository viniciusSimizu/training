package com.linkedrh.training.modules.curso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("curso")
public class CursoController {

    final Logger log = LoggerFactory.getLogger(CursoController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        this.log.debug("Opa");
        return "Opa";
    }
}
