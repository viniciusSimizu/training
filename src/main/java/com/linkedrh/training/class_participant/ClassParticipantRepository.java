package com.linkedrh.training.class_participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.class_participant.dtos.ClassParticipantCreateDTO;
import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.employee.EmployeeRepository;
import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class ClassParticipantRepository {

	private String table = "classes_participants";

	@Autowired
	private DatabaseConnection dbConnection;

	@Autowired
	private EmployeeRepository employeeRepository;

	public int create(ClassParticipantCreateDTO classParticipant) throws SQLException {
		String query = """
			INSERT INTO %s
			(class_code, employee_code)
			VALUES (?, ?)
			RETURNING code
			""";

		int classParticipantCode;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, classParticipant.classCode);
			pstmt.setInt(2, classParticipant.employeeCode);

			ResultSet result = pstmt.executeQuery();
			result.next();

			classParticipantCode = result.getInt("code");

			result.close();
		}

		return classParticipantCode;
	}

	public void delete(int code) throws SQLException {
		String query = """
			DELETE FROM %s
			WHERE code = ?
			""";

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
		}
	}

	public List<ClassParticipant> listByClass(int classCode) throws SQLException {
		String query = """
			SELECT
				code, employee_code
			FROM %s
			WHERE class_code = ?
			""";
		List<ClassParticipant> classParticipant = new ArrayList<>();

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, classCode);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int code = result.getInt("code");
				int employeeCode = result.getInt("employee_code");

				ClassParticipant participant = new ClassParticipant(code, classCode, employeeCode);
				classParticipant.add(participant);
			}
		}

		return classParticipant;
	}

	public ClassParticipant findByCode(int classParticipantCode) throws SQLException {
		String query = """
			SELECT
				class_code, employee_code
			FROM %s
			WHERE code = ?
			""";
		ClassParticipant classParticipant = null;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, classParticipantCode);
			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				int classCode = result.getInt("class_code");
				int employeeCode = result.getInt("employee_code");

				classParticipant = new ClassParticipant(classParticipantCode, classCode, employeeCode);
			}
		}

		return classParticipant;
	}
}
