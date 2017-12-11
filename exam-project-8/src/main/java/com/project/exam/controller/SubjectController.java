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

	@POST
	@Path("/GetSubjectByParameters")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getSubjectByParameters(@FormParam("program_id") int program_id,
			@FormParam("subject_name") String subject_name, @FormParam("subject_code") String subject_code,
			@FormParam("semester_no") int semester_no) {
		System.out.println("from controller =" + program_id + " " + subject_name + subject_code + " " + semester_no);
		Object[] ob = new Object[11];
		ob[1] = program_id;
		ob[2] = subject_name;
		ob[3] = subject_code;
		ob[9] = semester_no;
		return subjectService.getSubjectByParameters(ob);
	}

	@GET
	@Path("/GetSubjectByParameters1/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getSubjectByParameters1(@PathParam("id") int programId) {

		Object[] ob = new Object[11];
		ob[1] = programId;
		return subjectService.getSubjectByParameters(ob);
	}

	@POST
	@Path("/GetSubjectByProgram")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getSubjectByProgram(@FormParam("programId") int programId, @FormParam("semester_no") int semester_no) {
		System.out.println("program_id: "+ programId +" semester_no: "+ semester_no);
		
		Object[] ob = new Object[11];
		ob[1] = programId;
		ob[9] = semester_no;
		return subjectService.getSubjectByProgram(ob);
	}
}
