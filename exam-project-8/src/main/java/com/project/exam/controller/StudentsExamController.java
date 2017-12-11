package com.project.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.project.exam.model.Exam;
import com.project.exam.model.StudentsExam;
import com.project.exam.model.StudentsProgram;
import com.project.exam.model.Subjects;
import com.project.exam.services.ExamService;
import com.project.exam.services.StudentsExamService;
import com.project.exam.services.StudentsProgramService;
import com.project.exam.services.SubjectService;

@Path("/ApiStudentsExams")
public class StudentsExamController {
	@Autowired
	private StudentsExamService studentsExamService;
	@Autowired
	private StudentsProgramService studentsProgramService;
	@Autowired
	private SubjectService subjectser;

	@Autowired
	private ExamService examservice;

	@GET
	@Path("/GetAllStudentsExams")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<StudentsExam> getAllStudentsExam() {
		System.out.println("indiseee");
		return studentsExamService.getstudentsExam();
	}

	@POST
	@Path("/SaveStudentsExam")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StudentsExam saveStudentsExam(StudentsExam studentsExamModel) {
		return studentsExamService.addstudentsExam(studentsExamModel);
	}

	@GET
	@Path("/GetStudentsExams/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StudentsExam getStudentsExam(@PathParam("id") int id) {
		return studentsExamService.getstudentsExam(id);
	}

	@PUT
	@Path("/UpdateStudentsExam")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StudentsExam updateStudentsExam(StudentsExam studentsExamModel) {
		System.out.println("oppp=="+studentsExamModel.toString());
		return studentsExamService.updatestudentsExam(studentsExamModel);
	}

	@DELETE
	@Path("/DeleteStudentsExam/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public int deleteStudentsExamsModel(@PathParam("id") int id) {
		return studentsExamService.deletestudentsExam(id);
	}

	@POST
	@Path("/GetRequiredInfoTOSave")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void getRequiredInfoTOSave(@FormParam("a_program_id") int a_program_id,
			@FormParam("examTypeId") int examTypeId, @FormParam("semester_no") int semester_no) {
		System.out.println("a_program_id examTypeId semester_no" + a_program_id + examTypeId + semester_no);

		studentsExamService.getRequiredInfoTOSave(a_program_id, examTypeId, semester_no);
	}

	@POST
	@Path("/GetRequiredInfoTOupdate")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List getRequiredInfoTOupdate(@FormParam("semesterNo") int semesterNo,
			@FormParam("programeName") String programeName, @FormParam("programId") int programId,
			@FormParam("batchyear") int batchyear, @FormParam("examTypeName") String examTypeName,
			@FormParam("examtypeId") int examtypeId, @FormParam("subjectId") int subjectId,
			@FormParam("subjectName") String subjectName

	) {
		System.out.println("semesterNo = " + semesterNo);
		System.out.println("programeName = " + programeName);
		System.out.println("programId = " + programId);
		System.out.println("batchyear = " + batchyear);
		System.out.println("examTypeName = " + examTypeName);
		System.out.println("subjectId = " + subjectId);
		System.out.println("examtypeId = " + examtypeId);
		System.out.println("subjectName = " + subjectName);

		System.out.println(studentsExamService.updatestudentExamModel(semesterNo, programeName, programId, batchyear,
				examTypeName, subjectId, examtypeId, subjectName));

		return studentsExamService.updatestudentExamModel(semesterNo, programeName, programId, batchyear, examTypeName,
				subjectId, examtypeId, subjectName);
	}

	@POST
	@Path("/loadResultExams")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List loadResultExams(@FormParam("semester_no") int semester_no, @FormParam("exam_type_id") int exam_type_id,
			@FormParam("studentId") int studentId

	) {
		System.out.println("semesterNo = " + semester_no);
		System.out.println("programeName = " + exam_type_id);
		System.out.println("programId = " + studentId);

		List list = new ArrayList<>();
		StudentsProgram pr = studentsProgramService.getStudentsProgramByStudentId(studentId);
		int program_id = pr.getProgram_id();

		Object[] obj = new Object[15];
		obj[1] = program_id;
		obj[9] = semester_no;

		List<Subjects> sub = subjectser.getSubjectByParameters(obj);
		for (Subjects subjects : sub) {
			int subjID = subjects.getSubject_id();
			Object[] obj1 = new Object[15];
			obj[2] = subjID;
			obj[1] = exam_type_id;

			List<Exam> examList = examservice.searchByField(obj1);
			for (Exam exam : examList) {
				int exam_id = exam.getExam_id();
				StudentsExam stexam = studentsExamService.getstudentsExam(studentId, exam_id);
				Map<String, Object> st = new HashMap<>();
				st.put("student_exam_id", stexam.getStudents_exams_id());
				st.put("exam_id", stexam.getExam_id());
				st.put("attandence_status", stexam.getAttendance_status());
				st.put("obtain_marks", stexam.getObtained_marks());
				st.put("grade", stexam.getGrade());
				st.put("status", stexam.getStatus());
				st.put("subjectname", subjects.getSubject_name());
				st.put("fullmarks", exam.getFull_marks());
				st.put("passmarks", exam.getPass_marks());
				list.add(st);
			}
		}

		return list;
		// return
		// studentsExamService.updatestudentExamModel(semesterNo,programeName,programId,batchyear,examTypeName,subjectId,examtypeId,subjectName);
	}

}
