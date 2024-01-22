package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.curso.dtos.request.UpdateCursoBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateCursoBodyDTOTests {

    public UpdateCursoBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new UpdateCursoBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        String nome = "Curso";
        String descricao = "Exemplo";
        int duracao = 1;

        this.body.nome = nome;
        this.body.descricao = descricao;
        this.body.duracao = duracao;

        assertTrue(this.body.isValid());

        assertTrue(this.body.nome == nome);
        assertTrue(this.body.descricao == descricao);
        assertTrue(this.body.duracao == duracao);
    }
}
