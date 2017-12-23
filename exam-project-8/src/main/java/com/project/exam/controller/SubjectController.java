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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.exam.model.Subjects;
import com.project.exam.services.SubjectService;

@Path("/ApiSubject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GET
	@Path("/GetAllSubject")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Subjects> getAllSubject() {
		return subjectService.getallSubjectList();
	}

	@POST
	@Path("/SaveSubject")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Subjects saveSubject(Subjects sub) {
		System.out.println(sub.toString());
		return subjectService.addSubject(sub);
	}

	@GET
	@Path("/GetSubject/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Subjects> getSubject(@PathParam("id") int id) {
		return subjectService.getSubject(id);
	}

	@PUT
	@Path("/UpdateSubject")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Subjects updateSubject(Subjects subjectModel) {
		return subjectService.updateSubject(subjectModel);
	}

	@DELETE
	@Path("/DeleteSubject/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public int deleteSubject(@PathParam("id") int id) {
		return subjectService.deleteSubject(id);
	}

	@POST
	@Path("/SearchSubject")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List searchSubject(@FormParam("val") String params) {
		// System.out.println("from controller=
		// "+studentService.searchStudent(params).toString());

		return subjectService.searchSubject(params);

	}

	@GET
	@Path("/GetSubjectByParameters/{programId}/{semester}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getSubjectByParameters(@PathParam("programId") int programId,@PathParam("semester") int semester) {
		return subjectService.getSubjectByParameters(programId,semester);
	}

	@GET
	@Path("/GetSubjectByProgram/{programId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getSubjectByProgram(@PathParam("programId") int programId) {
		return subjectService.getSubjectByProgram(programId);
	}
	
}
