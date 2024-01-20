package com.linkedrh.training.modules.curso;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.curso.dtos.CreateCursoBodyDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CursoRepository {

    final Logger log = LoggerFactory.getLogger(CursoRepository.class);

    @Autowired private IDatabaseManager sqlManager;

    public int create(CreateCursoBodyDTO body) throws Exception {
        final String query =
                """
				INSERT INTO curso
				(nome, descricao, duracao)
				VALUES (?, ?, ?)
				RETURNING codigo
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.descricao);
            pstmt.setInt(3, body.duracao);

            ResultSet result = pstmt.executeQuery();
            result.next();

            this.log.debug(result.getStatement().toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Curso");
        }
    }

    public void delete(int cursoId) throws Exception {
        final String queryCurso = """
					DELETE FROM curso
					WHERE codigo = ?
				""";
        final String queryTurma = """
					DELETE FROM turma
					WHERE curso_id = ?
					""";
        final String queryParticipante =
                """
								DELETE FROM turma_participante AS participante
								USING turma
								WHERE
									participante.turma_id = turma.codigo
									AND turma.curso_id = ?
					""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmtCurso = conn.prepareStatement(queryCurso);
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

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível deletar o Curso");
        }
    }

    public void updateAtivoField(int cursoId, boolean ativo) throws Exception {
        final String query = """
				UPDATE curso
				SET ativo = ?
				WHERE codigo = ?
				""";

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
}
