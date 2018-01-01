package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.StudentsExam;

public interface StudentsExamDAO {
	public List<StudentsExam> getstudentsExamList();
	public int addstudentsExam(StudentsExam studentsExam);
	public List<StudentsExam> getstudentsExam(int s_Id);
	public StudentsExam updatestudentsExam(StudentsExam studentsExam);
	public int deletestudentsExam(int s_Id);
	public void getRequiredInfoTOSave(int a_program_id, int examTypeId, int semester_no);

	public List updatestudentExamModel(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName);

}
