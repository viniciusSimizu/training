package com.linkedrh.training.class_;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedrh.training.class_.dtos.ClassCreateDTO;
import com.linkedrh.training.class_.dtos.ClassUpdateDTO;

@RestController
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody ClassCreateDTO body) {
		try {
			int classCode = this.service.create(body);

			Map<String, Object> response = new HashMap<>();
			response.put("classCode", classCode);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().body(err.getMessage());
		}
	}

	@PutMapping("/{code}")
	public ResponseEntity<Object> update(@PathVariable int code, @RequestBody ClassUpdateDTO body) {
		try {
			this.service.update(code, body);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {
			return ResponseEntity.internalServerError().body(err.getMessage());
		}
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Object> delete(@PathVariable int code) {
		try {
			this.service.delete(code);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception err) {
			return ResponseEntity.internalServerError().body(err.getMessage());
		}
	}

	@GetMapping("/{courseCode}")
	public ResponseEntity<Object> listByCourse(@PathVariable int courseCode) {
		try {
			List<Class> classes = this.service.listByCourse(courseCode);
			return ResponseEntity.status(HttpStatus.OK).body(classes);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().body(err.getMessage());
		}
	}
}
