package com.linkedrh.training.modules.curso;

import com.linkedrh.training.lib.helpers.QueryFinder;
import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.curso.dtos.request.CreateCursoBodyDTO;
import com.linkedrh.training.modules.curso.dtos.request.UpdateCursoBodyDTO;
import com.linkedrh.training.modules.curso.entity.Curso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    private final String module = "curso";
    private final Logger log = LoggerFactory.getLogger(CursoRepository.class);

    @Autowired private IDatabaseManager sqlManager;
    @Autowired private QueryFinder qf;

    public int create(CreateCursoBodyDTO body) throws Exception {
        final String query = qf.findQuery(module, "create");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.descricao);
            pstmt.setInt(3, body.duracao);

            ResultSet result = pstmt.executeQuery();
            result.next();

            this.log.debug(pstmt.toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Curso");
        }
    }

    public List<Curso> list() throws Exception {
        final String query = this.qf.findQuery(module, "list");

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            ResultSet result = pstmt.executeQuery();

            this.log.debug(pstmt.toString());

            while (result.next()) {
                Curso item = new Curso();
                item.nome = result.getString("nome");
                item.duracao = result.getInt("duracao");
                item.ativo = result.getBoolean("ativo");
                item.quantidadeTurmas = result.getInt("quantidadeTurmas");
                item.dataInicioMaisProxima = result.getDate("dataInicioMaisProxima");

                cursos.add(item);
            }

            result.close();

        } catch (Exception e) {
            this.log.error(e.getMessage());
            throw new Exception("Não foi possível listar os cursos");
        }

        return cursos;
    }

    public void delete(int cursoId) throws Exception {
        final String queryCurso = this.qf.findQuery(module, "delete_curso");
        final String queryTurma = this.qf.findQuery(module, "delete_turma");
        final String queryParticipante = this.qf.findQuery(module, "delete_participante");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmtCurso = conn.prepareStatement(queryCurso);
                PreparedStatement pstmtTurma = conn.prepareStatement(queryTurma);
                PreparedStatement pstmtParticipante = conn.prepareStatement(queryParticipante); ) {
            conn.setAutoCommit(false);

            pstmtParticipante.setInt(1, cursoId);
            pstmtParticipante.executeUpdate();
            this.log.debug(pstmtParticipante.toString());

            pstmtTurma.setInt(1, cursoId);
            pstmtTurma.executeUpdate();
            this.log.debug(pstmtTurma.toString());

            pstmtCurso.setInt(1, cursoId);
            pstmtCurso.executeUpdate();
            this.log.debug(pstmtCurso.toString());

            conn.commit();

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível deletar o Curso");
        }
    }

    public void update(int cursoId, UpdateCursoBodyDTO body) throws Exception {
        final String query = this.qf.findQuery(module, "update");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.descricao);
            pstmt.setInt(3, body.duracao);
            pstmt.setInt(4, cursoId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            this.log.error(e.getMessage());
            throw new Exception("Não foi possível alterar o Curso");
        }
    }

    public void updateAtivoField(int cursoId, boolean ativo) throws Exception {
        final String query = this.qf.findQuery(module, "update_ativo");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setBoolean(1, ativo);
            pstmt.setInt(2, cursoId);

            pstmt.executeUpdate();

        } catch (SQLException except) {
            this.log.error(except.getMessage());
            throw new Exception("Não foi possível alterar o campo 'ativo' do Curso");
        }
    }

    public boolean hasTurmas(int cursoId) throws Exception {
        final String query = this.qf.findQuery(module, "has_turmas");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, cursoId);
            ResultSet result = pstmt.executeQuery();

            boolean ativo = result.next();
            result.close();

            return ativo;

        } catch (SQLException except) {
            this.log.error(except.getMessage());
            throw new Exception("Não foi possível alterar o campo 'ativo' do Curso");
        }
    }
}
