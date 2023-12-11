package com.linkedrh.training.course.service;

import java.util.List;

import com.linkedrh.training.course.dtos.CourseCreateDTO;
import com.linkedrh.training.course.dtos.CourseResponseDTO;
import com.linkedrh.training.course.dtos.CourseUpdateDTO;

public interface ICourseService {
	public List<CourseResponseDTO> listCourses() throws Exception;
	public int createCourse(CourseCreateDTO body) throws Exception;
	public void updateCourse(int courseCode, CourseUpdateDTO body) throws Exception;
	public void deleteCourse(int courseCode) throws Exception;
}
