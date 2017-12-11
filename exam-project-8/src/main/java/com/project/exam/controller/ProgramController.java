package com.project.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.project.exam.model.Program;
import com.project.exam.services.ProgramService;

@Path("/ApiProgram")
public class ProgramController {
	
	@Autowired
	private ProgramService programService;
	
	@GET
	@Path("/GetAllProgram")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Program> getAllProgram() {
		return programService.getProgramList();
	}
	
	
	@POST
	@Path("/SaveProgram")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Program saveProgram(Program program) {
		System.out.println(program.toString());
		return programService.addProgram(program);
	}
	

	@GET
	@Path("/GetProgram/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Program> getProgram(@PathParam("id") int id) {
		System.out.println("it is hercie = "+id);
		System.out.println(programService.getProgram(id).toString());
		return programService.getProgram(id);
	}
	
	@PUT
	@Path("/UpdateProgram")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Program updateProgram(Program program) {
		System.out.println(program.toString());
		return programService.updateProgram(program);
	}
	
	@DELETE
	@Path("/DeleteProgram/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteProgram(@PathParam("id") int id) {
		return programService.deleteProgram(id);
	}
	
	@GET
	@Path("/GetProgramByFacultyId/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Program> getProgramByFacultyId(@PathParam("id") int id) {
		return programService.getProgramListByFacultyId(id);
	}
	
	@POST
	@Path("/SearchProgram")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List searchProgram(@FormParam("val") String params) {
		//System.out.println("from controller= "+studentService.searchStudent(params).toString());
	return programService.searchProgram(params);
		
	}
}
