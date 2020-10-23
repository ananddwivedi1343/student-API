package com.anand.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anand.demo.model.Student;
import com.anand.demo.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@Test
	public void retrieveDetailsForStudents() throws Exception {
		Student mockStudent1 = new Student(1, "Anand", 24, "MCA", "Ramswaroop College");

		Mockito.when(studentService.getStudent(Mockito.anyInt())).thenReturn(mockStudent1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:\"Anand\",age:24,course:\"MCA\",college:\"Ramswaroop College\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getAllStudents() throws Exception {
		List<Student> allStudents = new ArrayList<>();
		allStudents.add(new Student(1, "Anand", 24, "MCA", "Ramswaroop College"));
		allStudents.add(new Student(2, "Abhishek", 22, "BCA", "BBD College"));
		allStudents.add(new Student(3, "Sumit", 26, "M.tech", "Ramswaroop College"));
		Mockito.when(studentService.getAllStudents()).thenReturn(allStudents);
		MvcResult result = mockMvc

				.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void createStudent() throws Exception {
		Student mockStudent1 = new Student(1, "Anand", 24, "MCA", "Ramswaroop College");

		String exampleCourseJson = "{\"id\":1,\"name\":\"Anand\",\"age\":24,\"course\":\"MCA\",\"college\":\"Ramswaroop College\"}";
		Mockito.when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(mockStudent1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students").accept(MediaType.APPLICATION_JSON)
				.content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/1", response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void updateStudent() throws Exception {
		Student mockStudent1 = new Student(1, "Anand", 24, "MCA", "Ramswaroop College");

		String exampleCourseJson = "{\"id\":1,\"name\":\"Anand\",\"age\":24,\"course\":\"MCA\",\"college\":\"BBD College\"}";
		Mockito.when(studentService.updateStudent(Mockito.anyInt(), Mockito.any(Student.class)))
				.thenReturn(mockStudent1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/students/1").accept(MediaType.APPLICATION_JSON)
				.content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/1", response.getHeader(HttpHeaders.LOCATION));
	}

	@Test
	public void deleteStudent() throws Exception {

		String uri = "/students/29";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void findByName() throws Exception {
		List<Student> allStudents = new ArrayList<>();
		allStudents.add(new Student(1, "Anand", 24, "MCA", "Ramswaroop College"));
		allStudents.add(new Student(2, "Abhishek", 22, "BCA", "BBD College"));

		Mockito.when(studentService.findByName(Mockito.anyString())).thenReturn(allStudents);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/name?name=anand")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:\"Anand\",age:24,course:\"MCA\",college:\"Ramswaroop College\"}";

		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
