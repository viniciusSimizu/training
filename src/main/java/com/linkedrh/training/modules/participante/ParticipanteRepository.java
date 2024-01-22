package com.linkedrh.training.modules.participante;

import com.linkedrh.training.lib.helpers.QueryFinder;
import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.participante.dtos.request.CreateParticipanteBodyDTO;

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

    private final String module = "participante";
    private final Logger log = LoggerFactory.getLogger(ParticipanteRepository.class);

    @Autowired private IDatabaseManager sqlManager;
		@Autowired private QueryFinder qf;

    public int create(CreateParticipanteBodyDTO body) throws Exception {
			final String query = this.qf.findQuery(module, "create");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, body.funcionarioId);
            pstmt.setInt(2, body.turmaId);

            ResultSet result = pstmt.executeQuery();
            result.next();

            this.log.debug(pstmt.toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar a Turma");
        }
    }

    public void delete(int turmaId, int funcionarioId) throws Exception {
			final String query = this.qf.findQuery(module, "delete");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, funcionarioId);

            pstmt.executeUpdate();

            this.log.debug(pstmt.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar a Turma");
        }
    }

    public void deleteByFuncionario(int funcionarioId) throws Exception {
			final String query = this.qf.findQuery(module, "delete_by_funcionario");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, funcionarioId);

            pstmt.executeUpdate();

            this.log.debug(pstmt.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Funcionário");
        }
    }
}
