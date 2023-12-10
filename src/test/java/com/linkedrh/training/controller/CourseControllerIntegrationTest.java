package com.linkedrh.training.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedrh.training.course.CourseController;
import com.linkedrh.training.course.dtos.CourseCreateDTO;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@TestPropertySource
public class CourseControllerIntegrationTest {

	//@LocalServerPort
	private int port;

	//@Autowired
	private MockMvc mockMvc;

	//@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders
			.standaloneSetup(CourseController.class)
			.build();
	}

	// @Test
	public void createTest() throws Exception {
		String name = "Curso técnico em Recursos Humanos";
		String description = """
			Atualmente, devido às diversas transformações no contexto de 
			trabalho, a valorização do profissional e um excelente clima 
			organizacional são fatores fundamentais para o bom funcionamento 
			de uma empresa.
			""";
		int duration = 48_000;

		CourseCreateDTO course = new CourseCreateDTO(name, description, duration);
		ObjectMapper mapper = new ObjectMapper();

		ResultMatcher statusMatcher = MockMvcResultMatchers
			.status()
			.isOk();

		ResultMatcher contentMatcher = MockMvcResultMatchers
			.content()
			.contentType(MediaType.APPLICATION_JSON);

		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders
				.post("/course")
				.contentType("application/json")
				.content(mapper.writeValueAsString(course))
				.accept("application/json"));

		result.andExpect(statusMatcher).andExpect(contentMatcher);
	}

	// @Test
	public void updateTest() throws Exception {
	}
}
