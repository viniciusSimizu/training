package com.linkedrh.training.employee.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.employee.Employee;
import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

	private String table = "employee";

	@Autowired
	private DatabaseConnection dbConnection;

	@Override
	public List<Employee> list() throws SQLException {
		String query = """
			SELECT
				code, name, cpf, birth_day, role, admission, status
			FROM %s
			ORDER BY NOW() - admission DESC
			""";
		List<Employee> employees = new ArrayList<>();

		try (
				Connection conn = this.dbConnection.getConnection();
				Statement pstmt = conn.createStatement();
				ResultSet result = pstmt.executeQuery(String.format(query, this.table));
			) 
		{
			while (result.next()) {
				int code = result.getInt("code");
				String name = result.getString("name");
				String cpf = result.getString("cpf");
				String role = result.getString("role");
				LocalDate birthDay = result.getDate("birth_day").toLocalDate();
				LocalDate admission = result.getDate("admission").toLocalDate();
				boolean status = result.getBoolean("status");

				Employee employee = new Employee(code, name, cpf, role, birthDay, admission, status);
				employees.add(employee);
			}
		}

		return employees;
	}

	@Override
	public Employee findById(int employeeCode) throws SQLException {
		String query = """
			SELECT
				name, cpf, birth_day, role, admission, status
			FROM %s
			WHERE code = ?
			""";
		Employee employee = null;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			) 
		{
			pstmt.setInt(1, employeeCode);
			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				String name = result.getString("name");
				String cpf = result.getString("cpf");
				String role = result.getString("role");
				LocalDate birthDay = result.getDate("birth_day").toLocalDate();
				LocalDate admission = result.getDate("admission").toLocalDate();
				boolean status = result.getBoolean("status");

				employee = new Employee(employeeCode, name, cpf, role, birthDay, admission, status);
			}
		}

		return employee;
	}
}

