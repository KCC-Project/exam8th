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

import com.project.exam.model.Exam_type;
import com.project.exam.services.Exam_typeService;

@Path("/ApiExam_type")
public class Exam_typeController {
	@Autowired
	private Exam_typeService exam_typeService;
	
	@GET
	@Path("/GetAllExam_type")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Exam_type> getAllExam_type() {
		return exam_typeService.getExam_typeList();
	}
	
	
	@POST
	@Path("/SaveExam_type")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Exam_type saveExam_type(Exam_type exam_type) {
		return exam_typeService.addExam_type(exam_type);
	}
	

	@GET
	@Path("/GetExam_type/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Exam_type> getExam_type(@PathParam("id") int id) {
		return exam_typeService.getExam_type(id);
	}
	
	@PUT
	@Path("/UpdateExam_type")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Exam_type updateExam_type(Exam_type exam_type) {
		return exam_typeService.updateExam_type(exam_type);
	}
	
	@DELETE
	@Path("/DeleteExam_type/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteExam_type(@PathParam("id") int id) {
		return exam_typeService.deleteExam_type(id);
	}
	@POST
	@Path("/SearchExam-Type")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List searchProgram(@FormParam("val") String params) {
		System.out.println("inside search exam type");
		//System.out.println("from controller= "+studentService.searchStudent(params).toString());
	return exam_typeService.searchExamType(params);
		
	}
}
