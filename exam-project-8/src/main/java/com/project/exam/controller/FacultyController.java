package com.project.exam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.Faculty;
import com.project.exam.services.FacultyService;

@Path("/ApiFaculty")
public class FacultyController {

	@Autowired
	private FacultyService FacultyService;
	
	@GET
	@Path("/GetAllFaculty")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Faculty> getAllFaculty() {
		return FacultyService.getFacultyList();
	}
	
	
	@POST
	@Path("/SaveFaculty")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Faculty saveFaculty(Faculty faculty) {
		return FacultyService.addFaculty(faculty);
	}
	

	@GET
	@Path("/GetFaculty/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Faculty getFaculty(@PathParam("id") int id) {
		return FacultyService.getFaculty(id);
	}
	
	@PUT
	@Path("/UpdateFaculty")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Faculty updateFaculty(Faculty faculty) {
		System.out.println("from update faculty="+faculty.toString());
		return FacultyService.updateFaculty(faculty);
	}
	
	@DELETE
	@Path("/DeleteFaculty/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteFaculty(@PathParam("id") int id) {
		return FacultyService.deleteFaculty(id);
	}
	
	
}
