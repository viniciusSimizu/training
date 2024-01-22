package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.funcionario.dtos.request.UpdateFuncionarioBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UpdateFuncionarioBodyDTOTests {

    public UpdateFuncionarioBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new UpdateFuncionarioBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        String nome = "Nome";
        String cpf = "111.111.111-11";
        String formatedCpf = "11111111111";
        String cargo = "Exemplo";
        LocalDate nascimento = LocalDate.now().minusWeeks(1);
        LocalDate admissao = nascimento.plusDays(1);

        this.body.nome = nome;
        this.body.cpf = cpf;
        this.body.cargo = cargo;
        this.body.nascimento = nascimento;
        this.body.admissao = admissao;

        assertTrue(this.body.isValid());

        assertTrue(nome.equals(this.body.nome));
        assertTrue(formatedCpf.equals(this.body.cpf));
        assertTrue(nascimento.isEqual(this.body.nascimento));
        assertTrue(cargo.equals(this.body.cargo));
        assertTrue(admissao.isEqual(this.body.admissao));
    }
}
