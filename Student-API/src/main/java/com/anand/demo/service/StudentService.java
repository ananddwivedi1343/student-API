package com.anand.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.demo.exception.StudentNotFoundException;
import com.anand.demo.model.Student;
import com.anand.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	public Student addStudent(Student student) {
		return studentRepo.save(student);

	}

	public Student getStudent(int id) {
		Optional<Student> student = studentRepo.findById(id);
		if (!student.isPresent())
			throw new StudentNotFoundException("Student with id: " +id+ " doesn't exist");
		return student.get();

	}

	public Student updateStudent(int id, Student student) {
		return studentRepo.save(student);
	}

	public void deleteStudent(int id) {

		studentRepo.deleteById(id);

	}

	public List<Student> findByName(String name) {
		return studentRepo.findByName(name);
	}

	public List<Student> findByCollege(String college) {
		return studentRepo.findByCollege(college);
	}

}