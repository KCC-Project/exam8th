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

import com.project.exam.model.Exam;
import com.project.exam.model.StudentsProgram;
import com.project.exam.services.ExamService;

@Path("ApiExam")
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@GET
	@Path("/GetAllExam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Exam> getAllExam() {
		return examService.getExamList();
	}
	
	
	@POST
	@Path("/SaveExam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int saveExam(Exam exam) {
		return examService.addExam(exam);
	}
	

	@GET
	@Path("/GetExam/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Exam> getExam(@PathParam("id") int id) {
		return examService.getExam(id);
	}
	
	@PUT
	@Path("/UpdateExam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Exam updateExam(Exam exam) {
		return examService.updateExam(exam);
	}
	
	@DELETE
	@Path("/DeleteExam/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteExam(@PathParam("id") int id) {
		return examService.deleteExam(id);
	}
	
	@GET
	@Path("/GetExamByExamTypeAndSubjectId/{exmaTypeId}/{subjectId}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Exam> getExamByExamTypeAndSubjectId(@PathParam("exmaTypeId") int exmaTypeId,@PathParam("subjectId") int subjectId) {
		
		return examService.searchByField(exmaTypeId,subjectId);
	}
	
}
