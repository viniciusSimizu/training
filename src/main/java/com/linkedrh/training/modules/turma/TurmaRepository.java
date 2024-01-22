package com.linkedrh.training.modules.turma;

import com.linkedrh.training.lib.helpers.QueryFinder;
import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.turma.dtos.request.CreateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.dtos.request.UpdateTurmaBodyDTO;
import com.linkedrh.training.modules.turma.entity.Turma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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

    private final String module = "turma";
    final Logger log = LoggerFactory.getLogger(TurmaRepository.class);

    @Autowired private IDatabaseManager sqlManager;
    @Autowired private QueryFinder qf;

    public int create(CreateTurmaBodyDTO body) throws Exception {
        final String query = this.qf.findQuery(module, "create");

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

    public List<Turma> listByCurso(int cursoId) throws IOException {
        final String query = this.qf.findQuery(module, "list_by_curso");

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

    public List<Turma> listBetweenDateRange(int cursoId, LocalDate inicio, LocalDate fim)
            throws IOException {
        final String query = this.qf.findQuery(module, "list_between_date_range");

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

    public Turma findByCursoAndFuncionario(int cursoId, int funcionarioId) throws IOException {
        final String query = this.qf.findQuery(module, "find_by_curso_and_funcionario");

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
        final String query = this.qf.findQuery(module, "update");

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
        final String queryParticipante = this.qf.findQuery(module, "delete_participante");
        final String queryTurma = this.qf.findQuery(module, "delete_turma");

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
