package com.linkedrh.training.modules.turma;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.turma.dtos.request.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.request.UpdateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

            this.log.debug(pstmt.toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar a Turma");
        }
    }

    public List<Turma> listByCurso(int cursoId) {
        final String query =
                """
				SELECT
					turma.codigo AS codigo,
					turma.inicio AS inicio,
					turma.fim AS fim,
					turma.local AS local,
					COUNT(participante.codigo) AS quantidadeParticipantes
				FROM turma
				LEFT JOIN turma_participante participante ON participante.turma_id = turma.codigo
				WHERE curso_id = ?
				GROUP BY turma.codigo
				ORDER BY inicio, fim
				""";

        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, cursoId);
            ResultSet result = pstmt.executeQuery();

            this.log.debug(pstmt.toString());

            while (result.next()) {
                Turma turma = new Turma();
                turma.codigo = result.getInt("codigo");
                turma.inicio = result.getDate("inicio");
                turma.fim = result.getDate("fim");
                turma.local = result.getString("local");
                turma.quantidadeParticipantes = result.getInt("quantidadeParticipantes");

                turmas.add(turma);
            }
            result.close();

        } catch (SQLException err) {
            this.log.error(err.getMessage());
        }

        return turmas;
    }

    public List<Turma> listBetweenDateRange(int cursoId, LocalDate inicio, LocalDate fim) {
        final String query =
                """
				SELECT
					turma.codigo AS codigo,
					turma.inicio AS inicio,
					turma.fim AS fim,
					turma.local AS local,
					COUNT(participante.codigo) AS quantidadeParticipantes
				FROM turma
				LEFT JOIN turma_participante participante ON participante.turma_id = turma.codigo
				WHERE turma.curso_id = ?
				AND turma.inicio BETWEEN ? AND ?
				GROUP BY turma.codigo
				ORDER BY inicio, fim
				""";

        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, cursoId);
            pstmt.setDate(2, Date.valueOf(inicio));
            pstmt.setDate(3, Date.valueOf(fim));
            ResultSet result = pstmt.executeQuery();

            this.log.debug(pstmt.toString());

            while (result.next()) {
                Turma turma = new Turma();
                turma.codigo = result.getInt("codigo");
                turma.inicio = result.getDate("inicio");
                turma.fim = result.getDate("fim");
                turma.local = result.getString("local");
                turma.quantidadeParticipantes = result.getInt("quantidadeParticipantes");

                turmas.add(turma);
            }
            result.close();

        } catch (SQLException err) {
            this.log.error(err.getMessage());
        }

        return turmas;
    }

    public Turma findTurmaByCursoAndFuncionario(int cursoId, int funcionarioId) {
        final String query =
                """
				SELECT
					turma.codigo AS codigo,
					turma.inicio AS inicio,
					turma.fim AS fim,
					turma.local AS local
				FROM turma
				INNER JOIN turma_participante participante
					ON participante.turma_id = turma.codigo
				WHERE turma.curso_id = ?
					AND participante.funcionario_id = ?
				GROUP BY turma.codigo
				ORDER BY inicio DESC
				LIMIT 1
				""";

        Turma turma = null;

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, cursoId);
            pstmt.setInt(2, funcionarioId);
            ResultSet result = pstmt.executeQuery();

            this.log.debug(pstmt.toString());

            if (result.next()) {
                turma = new Turma();
                turma.codigo = result.getInt("codigo");
                turma.inicio = result.getDate("inicio");
                turma.fim = result.getDate("fim");
                turma.local = result.getString("local");
            }
            result.close();

        } catch (SQLException err) {
            this.log.error(err.getMessage());
        }

        return turma;
    }

    public void update(int turmaId, UpdateTurmaBodyDTO body) throws Exception {
        final String query =
                """
								UPDATE turma
								SET inicio = ?,
										fim = ?,
										local = ?
								WHERE codigo = ?
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setDate(1, Date.valueOf(body.inicio));
            pstmt.setDate(2, Date.valueOf(body.fim));
            pstmt.setString(3, body.local);

            pstmt.executeUpdate();

            this.log.debug(pstmt.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível atualizar a Turma");
        }
    }

    public void delete(int turmaId) throws Exception {
        final String queryParticipante =
                """
								DELETE FROM turma_participante
								WHERE turma_id = ?
				""";
        final String queryTurma = """
								DELETE FROM turma
								WHERE codigo = ?
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmtParticipante = conn.prepareStatement(queryParticipante);
                PreparedStatement pstmtTurma = conn.prepareStatement(queryTurma); ) {
            conn.setAutoCommit(false);

            pstmtParticipante.setInt(1, turmaId);
            pstmtParticipante.executeUpdate();
            this.log.debug(pstmtParticipante.toString());

            pstmtTurma.setInt(1, turmaId);
            pstmtTurma.executeUpdate();
            this.log.debug(pstmtTurma.toString());

            conn.commit();

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível deletar a Turma");
        }
    }
}
