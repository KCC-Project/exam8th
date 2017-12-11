package com.project.exam.JsonDataController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.exam.model.Student;
import com.project.exam.services.StudentService;

@RestController

@RequestMapping("/json")
public class JsonController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/students/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getStudentList() {
		return studentService.getStudentList();

	}
	
}
