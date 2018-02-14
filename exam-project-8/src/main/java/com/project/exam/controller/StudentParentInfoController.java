package com.project.exam.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.Admin;
import com.project.exam.model.StudentsParentInfo;
import com.project.exam.services.StudentsParentInfoService;

@Path("/ApiStudentParent")
public class StudentParentInfoController {

	@Autowired
	private StudentsParentInfoService studparent;
	
	
	@POST
	@Path("/SaveStudentParent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public StudentsParentInfo saveAdmin(StudentsParentInfo studentsParentInfo) {
		System.out.println(studentsParentInfo.toString());
		return studparent.addStudentsParentInfo(studentsParentInfo);
	}
	
	
	@PUT
	@Path("/UpdateStudentParent")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public StudentsParentInfo updateAdmin(StudentsParentInfo studentsParentInfo) {
		return studparent.updateStudentsParentInfo(studentsParentInfo);
	}
	
	@DELETE
	@Path("/DeleteStudentParent/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteAdmin(@PathParam("id") int id) {
		return studparent.deleteStudentsParentInfo(id);
	}
	
}
