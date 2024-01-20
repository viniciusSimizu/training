package com.linkedrh.training.modules.participante;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.participante.dtos.CreateParticipanteBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ParticipanteRepository {

    final Logger log = LoggerFactory.getLogger(ParticipanteRepository.class);

    @Autowired private IDatabaseManager sqlManager;

    public int create(CreateParticipanteBodyDTO body) throws Exception {
        final String query =
                """
				INSERT INTO turma_participante
				(funcionario_id, turma_id)
				VALUES (?, ?)
				RETURNING codigo
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, body.funcionarioId);
            pstmt.setInt(2, body.turmaId);

            ResultSet result = pstmt.executeQuery();
            result.next();

            this.log.debug(result.getStatement().toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar a Turma");
        }
    }
}
