package com.linkedrh.training.course.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkedrh.training.course.Course;
import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;
import com.linkedrh.training.shared.db.DatabaseConnection;

@Repository
public class CourseRepository implements ICourseRepository {

	private String table = "course";

	@Autowired
	private DatabaseConnection dbConnection;

	@Override
	public List<Course> list() throws SQLException {
		String query = """
			SELECT
				code, duration, name, description
			FROM %s
			ORDER BY code ASC
			""";
		List<Course> courses = new ArrayList<>();

		try (
				Connection conn = this.dbConnection.getConnection();
				Statement pstmt = conn.createStatement();
				ResultSet result = pstmt.executeQuery(String.format(query, this.table));
			) 
		{
			while (result.next()) {
				int code = result.getInt("code");
				int duration = result.getInt("duration");
				String name = result.getString("name");
				String description = result.getString("description");

				Course course = new Course(code, duration, name, description);
				courses.add(course);
			}
		}

		return courses;
	}

	@Override
	public int create(CourseCreateDTO course) throws SQLException {
		String query = """
			INSERT INTO %s
			(name, description, duration)
			VALUES (?, ?, ?)
			RETURNING code
			""";

		int courseCode;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setString(1, course.name);
			pstmt.setString(2, course.description);
			pstmt.setInt(3, course.duration);

			ResultSet result = pstmt.executeQuery();

			result.next();
			courseCode = result.getInt("code");

			result.close();
		}

		return courseCode;
	}

	@Override
	public void update(int code, CourseUpdateDTO course) throws SQLException {
		String query = """
			UPDATE %s
			SET name = ?, description = ?, duration = ?
			WHERE code = ?
			""";

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setString(1, course.name);
			pstmt.setString(2, course.description);
			pstmt.setInt(3, course.duration);
			pstmt.setInt(4, code);
			pstmt.executeUpdate();
		}
	}

	@Override
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

	@Override
	public Course findByCode(int courseCode) throws SQLException {
		String query = """
			SELECT
				name, description, duration
			FROM %s
			WHERE code = ?
			""";

		Course course = null;

		try (
				Connection conn = this.dbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(String.format(query, this.table));
			)
		{
			pstmt.setInt(1, courseCode);

			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				int duration = result.getInt("duration");
				String name = result.getString("name");
				String description = result.getString("description");
				course = new Course(courseCode, duration, name, description);
			}

			result.close();
		}

		return course;
	}
}
