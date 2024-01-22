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

        Connection conn = this.sqlManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query); ) {

            conn.setAutoCommit(false);

            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.descricao);
            pstmt.setInt(3, body.duracao);

            ResultSet result = pstmt.executeQuery();
            conn.commit();
            this.log.debug(pstmt.toString());

            result.next();
            int codigo = result.getInt("codigo");
            result.close();

            return codigo;

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Curso");

        } finally {
            conn.setAutoCommit(true);
            conn.close();
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
                item.codigo = result.getInt("codigo");
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

    public List<Curso> listBetweenDateRange() throws Exception {
        final String query = this.qf.findQuery(module, "list_between_date_range");

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {

            ResultSet result = pstmt.executeQuery();
            this.log.debug(pstmt.toString());

            while (result.next()) {
                Curso item = new Curso();
                item.codigo = result.getInt("codigo");
                item.nome = result.getString("nome");
                item.duracao = result.getInt("duracao");
                item.quantidadeTurmas = result.getInt("quantidadeTurmas");

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

        Connection conn = this.sqlManager.getConnection();
        try (PreparedStatement pstmtCurso = conn.prepareStatement(queryCurso);
                PreparedStatement pstmtTurma = conn.prepareStatement(queryTurma);
                PreparedStatement pstmtParticipante = conn.prepareStatement(queryParticipante); ) {

            conn.setAutoCommit(false);

            pstmtParticipante.setInt(1, cursoId);
            pstmtParticipante.executeUpdate();

            pstmtTurma.setInt(1, cursoId);
            pstmtTurma.executeUpdate();

            pstmtCurso.setInt(1, cursoId);
            pstmtCurso.executeUpdate();
            conn.commit();

            this.log.debug(pstmtParticipante.toString());
            this.log.debug(pstmtTurma.toString());
            this.log.debug(pstmtCurso.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível deletar o Curso");

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public void update(int cursoId, UpdateCursoBodyDTO body) throws Exception {
        final String query = this.qf.findQuery(module, "update");

        Connection conn = this.sqlManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query); ) {

            conn.setAutoCommit(false);

            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.descricao);
            pstmt.setInt(3, body.duracao);
            pstmt.setInt(4, cursoId);

            pstmt.executeUpdate();
            conn.commit();
            this.log.debug(pstmt.toString());

        } catch (SQLException e) {
            this.log.error(e.getMessage());
            throw new Exception("Não foi possível alterar o Curso");

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public void updateAtivoField(int cursoId, boolean ativo) throws Exception {
        final String query = this.qf.findQuery(module, "update_ativo");

        Connection conn = this.sqlManager.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query); ) {

            conn.setAutoCommit(false);

            pstmt.setBoolean(1, ativo);
            pstmt.setInt(2, cursoId);

            pstmt.executeUpdate();
            conn.commit();
            this.log.debug(pstmt.toString());

        } catch (SQLException except) {
            this.log.error(except.getMessage());
            throw new Exception("Não foi possível alterar o campo 'ativo' do Curso");

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public boolean hasTurmas(int cursoId) throws Exception {
        final String query = this.qf.findQuery(module, "has_turmas");

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {

            pstmt.setInt(1, cursoId);
            ResultSet result = pstmt.executeQuery();
            this.log.debug(pstmt.toString());

            boolean ativo = result.next();
            result.close();

            return ativo;

        } catch (SQLException except) {
            this.log.error(except.getMessage());
            throw new Exception("Não foi possível alterar o campo 'ativo' do Curso");
        }
    }
}
