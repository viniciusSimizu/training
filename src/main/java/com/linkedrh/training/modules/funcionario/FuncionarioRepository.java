package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.funcionario.dtos.CreateFuncionarioBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class FuncionarioRepository {

    final Logger log = LoggerFactory.getLogger(FuncionarioRepository.class);

    @Autowired private IDatabaseManager sqlManager;

    public int create(CreateFuncionarioBodyDTO body) throws Exception {
        final String query =
                """
				INSERT INTO funcionario
				(nome, cpf, nascimento, cargo, admissao)
				VALUES (?, ?, ?, ?, ?)
				RETURNING codigo
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.cpf);
            pstmt.setDate(3, Date.valueOf(body.nascimento));
            pstmt.setString(4, body.cargo);
            pstmt.setDate(5, Date.valueOf(body.admissao));

            ResultSet result = pstmt.executeQuery();
            this.log.debug(result.getStatement().toString());
            result.next();

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Funcionário");
        }
    }
}
