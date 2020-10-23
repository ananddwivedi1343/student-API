package com.anand.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByName(String name);

	List<Student> findByCollege(String college);

}
