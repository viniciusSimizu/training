package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.curso.dtos.request.CreateCursoBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCursoBodyDTOTests {

    public CreateCursoBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new CreateCursoBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        String nome = "Curso Exemplo";
        String description = "Descrição";
        int duracao = 90;

        this.body.nome = nome;
        this.body.descricao = description;
        this.body.duracao = duracao;

        assertTrue(this.body.isValid());

        assertTrue(this.body.nome == nome);
        assertTrue(this.body.descricao == description);
        assertTrue(this.body.duracao == duracao);
    }
}
