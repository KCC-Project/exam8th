package com.project.exam.services;

import java.util.List;

import com.project.exam.model.StudentsExam;

public interface StudentsExamService {
	public List<StudentsExam> getstudentsExam();
	public int addstudentsExam(StudentsExam studentsExam);
	public List<StudentsExam> getstudentsExam(int s_Id);
	public List<StudentsExam> getstudentsExam(int s_Id,int semesterNo);
	public StudentsExam updatestudentsExam(StudentsExam studentsExam);
	public int deletestudentsExam(int s_Id);
	public void getRequiredInfoTOSave(int a_program_id, int examTypeId, int semester_no);
	
	//this method must be changed
	public List updatestudentExamModel(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName);
	
	public List getstudentsExam(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName);
	
	List<StudentsExam> searchByField(int studentId);
	
}
