package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.turma.dtos.request.CreateTurmaBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CreateTurmaBodyDTOTests {

    public CreateTurmaBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new CreateTurmaBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        int cursoId = 1;
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.from(inicio);
        String local = "Indaiatuba";

        this.body.cursoId = cursoId;
        this.body.inicio = inicio;
        this.body.fim = fim;
        this.body.local = local;

        assertTrue(this.body.isValid());

        assertTrue(this.body.cursoId == cursoId);
        assertTrue(this.body.inicio == inicio);
        assertTrue(this.body.fim == fim);
        assertTrue(this.body.local == local);
    }
}
