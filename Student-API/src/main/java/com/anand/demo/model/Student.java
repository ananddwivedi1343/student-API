package com.anand.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 3, message = "Name should have atleast 3 characters")
	private String name;

	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 35, message = "Age should not be greater than 35")
	private int age;

	@NotBlank
	private String course;

	@NotBlank
	private String college;

	public Student() {

	}

	public Student(int id, String name, int age, String course, String college) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.course = course;
		this.college = college;
	}

	@Override
	public String toString() {

		return String.format("Student [id=%s, name=%s, age=%s, course=%s, college=%s]", id, name, age, course, college);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

}