package com.linkedrh.training.class_;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;
import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class ClassRepository {

	private String table = "class";

	@Autowired
	private DatabaseConnection dbConnection;

	public int create(ClassCreateDTO class_) throws SQLException {
		String query = """
			INSERT INTO %s
			(start_date, end_date, location, course_code)
			VALUES (?, ?, ?, ?)
			RETURNING code
			""";

		int classCode;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setDate(1, Date.valueOf(class_.start));
			pstmt.setDate(2, Date.valueOf(class_.end));
			pstmt.setString(3, class_.location);
			pstmt.setInt(4, class_.courseCode);

			ResultSet result = pstmt.executeQuery();

			result.next();
			classCode = result.getInt("code");

			result.close();
		}

		return classCode;
	}

	public void update(int code, ClassUpdateDTO class_) throws SQLException {
		String query = """
			UPDATE %s
			SET start_date = ?,
				end_date = ?,
				location = ?
			WHERE code = ?
					""";

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setDate(1, Date.valueOf(class_.startDate));
			pstmt.setDate(2, Date.valueOf(class_.endDate));
			pstmt.setString(3, class_.location);
			pstmt.setInt(4, code);

			pstmt.executeUpdate();
		}
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

	public List<Class> listByCourseCode(int courseCode) throws SQLException {
		String query = """
			SELECT
				code, start_date, end_date, location
			FROM %s
			WHERE course_code = ?
			""";
		List<Class> classes = new ArrayList<>();

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, courseCode);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int code = result.getInt("code");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				String location = result.getString("location");

				Class class_ = new Class(code, courseCode, startDate, endDate)
					.setLocation(location);
				classes.add(class_);
			}

			result.close();
		}

		return classes;
	}
	
	public Class findByCode(int classCode) throws SQLException {
		String query = """
			SELECT
				course_code, start_date, end_date, location
			FROM %s
			WHERE code = ?
			""";

		Class class_ = null;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, classCode);

			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				int courseCode = result.getInt("course_code");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				String location = result.getString("location");
				class_ = new Class(classCode, courseCode, startDate, endDate)
					.setLocation(location);
			}

			result.close();
		}

		return class_;
	}
}
