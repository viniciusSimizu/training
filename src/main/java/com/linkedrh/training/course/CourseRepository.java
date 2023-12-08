package com.linkedrh.training.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;
import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class CourseRepository {

	private String table = "course";

	@Autowired
	private DatabaseConnection dbConnection;

	public List<Course> list() throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				SELECT
					code, duration, name, description
				FROM course
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Course> courses = new ArrayList<>();
		while (resultSet.next()) {
			int code = resultSet.getInt("code");
			int duration = resultSet.getInt("duration");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			Course course = new Course(code, duration, name, description);
			courses.add(course);
		}

		resultSet.close();
		pstmt.close();
		conn.close();

		return courses;
	}

	public void create(CourseCreateDTO body) throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				INSERT INTO %s
				(name, description, duration)
				VALUES (?, ?, ?)
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, body.name);
		pstmt.setString(2, body.description);
		pstmt.setInt(3, body.duration);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void update(int code, CourseUpdateDTO course) throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				UPDATE %s
				SET name = ?, description = ?, duration = ?
				WHERE code = ?
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, course.name);
		pstmt.setString(2, course.description);
		pstmt.setInt(3, course.duration);
		pstmt.setInt(4, code);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void delete(int code) throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				DELETE FROM %s
				WHERE code = ?
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, code);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
}
