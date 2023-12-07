package com.linkedrh.training.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class CourseRepository {

	private String table = "Course";

	@Autowired
	private DatabaseConnection dbConnection;

	public List<Course> list() throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				SELECT
					Code, Duration, Name, Description
				FROM Course
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Course> courses = new ArrayList<>();

		while (resultSet.next()) {
			int code = resultSet.getInt("Code");
			int duration = resultSet.getInt("Duration");
			String name = resultSet.getString("Name");
			String description = resultSet.getString("Description");
			Course course = new Course(code, duration, name, description);
			courses.add(course);
		}

		conn.close();
		pstmt.close();
		resultSet.close();

		return courses;
	}

	public void create(Course course) throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				INSERT INTO %s
				(Name, Description, Duration)
				VALUES (?, ?, ?)
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, course.name());
		pstmt.setString(2, course.description());
		pstmt.setInt(3, course.duration());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public void update(Course course) throws SQLException {
		Connection conn = this.dbConnection.getConnection();
		String query = String.format("""
				UPDATE %s
				SET Name = ?, Description = ?, Duration = ?
				WHERE Code = ?
				""", this.table);

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, course.name());
		pstmt.setString(2, course.description());
		pstmt.setInt(3, course.duration());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
}
