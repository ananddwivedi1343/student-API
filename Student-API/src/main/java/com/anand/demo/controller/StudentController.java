package com.anand.demo.controller;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anand.demo.model.Student;

import com.anand.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();

	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {

		return studentService.getStudent(id);

	}

	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@Valid @RequestBody Student student) {
		
		Student savedStudent = studentService.addStudent(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable int id, @RequestBody Student student) {
		Student updatedStudent = studentService.updateStudent(id, student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(null)
				.buildAndExpand(updatedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
	}

	@GetMapping("/students/name")
	public List<Student> findByName(@RequestParam String name) {
		return studentService.findByName(name);
	}

	@GetMapping("/students/college")
	public List<Student> findByCollege(@RequestParam String college) {
		return studentService.findByCollege(college);
	}
}
