package com.project.exam.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.Student;
import com.project.exam.services.StudentService;

@Path("/ApiStudent")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GET
	@Path("/GetAllStudent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Student> getAllStudent() {
		System.out.println("indiseee");
		return studentService.getStudentList();
	}
	
	
	@POST
	@Path("/SaveStudent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student saveStudent(Student student ) {
		System.out.println("in save student");
		
		//return null;
		return studentService.addStudent(student);
	}
	

	@GET
	@Path("/GetStudent/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Student> getStudent(@PathParam("id") int id) {
		System.out.println("in get student field = "+id);
		System.out.println(studentService.getStudent(id));
		return studentService.getStudent(id);
	}
	
	@PUT
	@Path("/UpdateStudent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student updateStudent(Student studentModel) {
		return studentService.updateStudent(studentModel);
	}
	
	@DELETE
	@Path("/DeleteStudent/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteStudentsModel(@PathParam("id") int id) {
		return studentService.deleteStudent(id);
	}
	
	
	@POST
	@Path("/SearchStudent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List searchStudent(@FormParam("val") String params) {
		//System.out.println("from controller= "+studentService.searchStudent(params).toString());
		
	return studentService.searchStudent(params);
		
	}
	
	
	@POST
	@Path("/SearchStudentsByProgram")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List searchStudentsByProgram(@FormParam("programId") int programId,@FormParam("batchyear") int batchyear) {
		System.out.println("from controller =" +programId +" "+batchyear);
		Object[] ob= new Object[7];
		ob[2]=programId;
		ob[3]=batchyear;
		System.out.println("form server = "+studentService.getStudentsByStudentsProgram(ob));
		return studentService.getStudentsByStudentsProgram(ob);
	}
	
}
