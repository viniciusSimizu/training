package com.linkedrh.training.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseResponseDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;
import com.linkedrh.training.course.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService {

	private ICourseRepository repository;

	@Autowired
	public CourseService(ICourseRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CourseResponseDTO> listCourses() throws Exception {
		return this.repository.list()
			.stream()
			.map(CourseResponseDTO::buildFromEntity)
			.toList();
	}

	@Override
	public int createCourse(CourseCreateDTO body) throws Exception {
		return this.repository.create(body);
	}

	@Override
	public void updateCourse(int courseCode, CourseUpdateDTO body) throws Exception {
		this.repository.update(courseCode, body);
	}

	@Override
	public void deleteCourse(int courseCode) throws Exception {
		this.repository.delete(courseCode);
	}
}
