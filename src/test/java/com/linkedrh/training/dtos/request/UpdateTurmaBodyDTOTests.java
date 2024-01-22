package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.turma.dtos.request.UpdateTurmaBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UpdateTurmaBodyDTOTests {

    public UpdateTurmaBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new UpdateTurmaBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.from(inicio);
        String local = "Indaiatuba";

        this.body.inicio = inicio;
        this.body.fim = fim;
        this.body.local = local;

        assertTrue(this.body.isValid());

        assertTrue(this.body.inicio == inicio);
        assertTrue(this.body.fim == fim);
        assertTrue(this.body.local == local);
    }
}
