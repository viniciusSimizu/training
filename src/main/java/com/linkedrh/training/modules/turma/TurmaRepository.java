package com.linkedrh.training.modules.turma;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.turma.dtos.CreateTurmaBodyDTO;

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
public class TurmaRepository {

    final Logger log = LoggerFactory.getLogger(TurmaRepository.class);

    @Autowired private IDatabaseManager sqlManager;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        final String query =
                """
				INSERT INTO turma
				(inicio, fim, local, curso_id)
				VALUES (?, ?, ?, ?)
				RETURNING codigo
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setDate(1, Date.valueOf(body.inicio));
            pstmt.setDate(2, Date.valueOf(body.fim));
            pstmt.setString(3, body.local);
            pstmt.setInt(4, body.cursoId);

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
