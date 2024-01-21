package com.linkedrh.training.modules.funcionario;

import com.linkedrh.training.lib.interfaces.IDatabaseManager;
import com.linkedrh.training.modules.funcionario.dtos.request.CreateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.dtos.request.UpdateFuncionarioBodyDTO;
import com.linkedrh.training.modules.funcionario.entity.Funcionario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            result.next();

            this.log.debug(pstmt.toString());

            int codigo = result.getInt("codigo");
            result.close();

            return codigo;
        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível criar o Funcionário");
        }
    }

    public List<Funcionario> list(Boolean ativo) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append(
                """
					SELECT
						codigo, nome, cpf, nascimento, cargo, admissao, ativo
					FROM funcionario
				""");

        if (ativo == true) {
            query.append(" WHERE ativo = TRUE");
        } else if (ativo == false) {
            query.append(" WHERE ativo = FALSE");
        }

        query.append(" ORDER BY nome ASC");

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query.toString()); ) {
            ResultSet result = pstmt.executeQuery();
            this.log.debug(pstmt.toString());

            while (result.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.codigo = result.getInt("codigo");
                funcionario.nome = result.getString("nome");
                funcionario.cpf = result.getString("cpf");
                funcionario.nascimento = result.getDate("nascimento");
                funcionario.cargo = result.getString("cargo");
                funcionario.admissao = result.getDate("admissao");
                funcionario.ativo = result.getBoolean("ativo");

                funcionarios.add(funcionario);
            }

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível listar os Funcionários");
        }

        return funcionarios;
    }

    public List<Funcionario> listByTurma(int turmaId) {
        final String query =
                """
					SELECT
						funcionario.codigo AS codigo,
						funcionario.nome AS nome,
						funcionario.cpf AS cpf,
						funcionario.nascimento AS nascimento,
						funcionario.cargo AS cargo,
						funcionario.admissao AS admissao,
						funcionario.ativo AS ativo
					FROM funcionario
					INNER JOIN turma_participante participante
						ON participante.funcionario_id = funcionario.codigo
					WHERE participante.turma_id = ?
				""";

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setInt(1, turmaId);

            ResultSet result = pstmt.executeQuery();
            this.log.debug(pstmt.toString());

            while (result.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.codigo = result.getInt("codigo");
                funcionario.nome = result.getString("nome");
                funcionario.cpf = result.getString("cpf");
                funcionario.nascimento = result.getDate("nascimento");
                funcionario.cargo = result.getString("cargo");
                funcionario.admissao = result.getDate("admissao");
                funcionario.ativo = result.getBoolean("ativo");

                funcionarios.add(funcionario);
            }

        } catch (SQLException err) {
            this.log.error(err.getMessage());
        }

        return funcionarios;
    }

    public void update(int funcionarioId, UpdateFuncionarioBodyDTO body) throws Exception {
        final String query =
                """
								UPDATE funcionario
								SET nome = ?,
								cpf = ?,
								cargo = ?,
								nascimento = ?,
								admissao = ?
								WHERE codigo = ?
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setString(1, body.nome);
            pstmt.setString(2, body.cpf);
            pstmt.setString(3, body.cargo);
            pstmt.setDate(4, Date.valueOf(body.nascimento));
            pstmt.setDate(5, Date.valueOf(body.admissao));
            pstmt.setInt(6, funcionarioId);

            pstmt.executeUpdate();

            this.log.debug(pstmt.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível atualizar o Funcionário");
        }
    }

    public void updateAtivoField(int funcionarioId, boolean ativo) throws Exception {
        final String query =
                """
								UPDATE funcionario
								SET ativo = ?
								WHERE codigo = ?
				""";

        try (Connection conn = this.sqlManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); ) {
            pstmt.setBoolean(1, ativo);
            pstmt.setInt(2, funcionarioId);

            pstmt.executeUpdate();

            this.log.debug(pstmt.toString());

        } catch (SQLException err) {
            this.log.error(err.getMessage());
            throw new Exception("Não foi possível atualizar o campo 'ativo' do Funcionário");
        }
    }
}
