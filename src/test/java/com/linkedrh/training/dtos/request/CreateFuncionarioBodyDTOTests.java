package com.linkedrh.training.dtos.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linkedrh.training.modules.funcionario.dtos.request.CreateFuncionarioBodyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CreateFuncionarioBodyDTOTests {

    public CreateFuncionarioBodyDTO body;

    @BeforeEach
    public void setup() {
        this.body = new CreateFuncionarioBodyDTO();
    }

    @Test
    public void Correct_IsValid_Test() {
        String nome = "Estevan";
        String cpf = "111.111.111-11";
        String formatedCpf = "11111111111";
        LocalDate nascimento = LocalDate.of(2001, 1, 1);
        LocalDate admissao = nascimento.plusMonths(1);
        String cargo = "ADM";

        this.body.nome = nome;
        this.body.cpf = cpf;
        this.body.nascimento = nascimento;
        this.body.cargo = cargo;
        this.body.admissao = admissao;

        assertTrue(this.body.isValid());

        assertTrue(nome.equals(this.body.nome));
        assertTrue(formatedCpf.equals(this.body.cpf));
        assertTrue(nascimento.isEqual(this.body.nascimento));
        assertTrue(cargo.equals(this.body.cargo));
        assertTrue(admissao.isEqual(this.body.admissao));
    }
}
