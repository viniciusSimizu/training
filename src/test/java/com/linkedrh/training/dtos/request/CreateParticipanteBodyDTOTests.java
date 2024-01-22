package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.participante.dtos.request.CreateParticipanteBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateParticipanteBodyDTOTests {

    public CreateParticipanteBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new CreateParticipanteBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        int turmaId = 1;
        int funcionarioId = 1;

        this.body.turmaId = turmaId;
        this.body.funcionarioId = funcionarioId;

        assertTrue(this.body.isValid());

        assertTrue(this.body.turmaId == turmaId);
        assertTrue(this.body.funcionarioId == funcionarioId);
    }
}
