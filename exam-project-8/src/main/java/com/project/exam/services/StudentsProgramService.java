package com.project.exam.services;

import java.util.List;

import com.project.exam.model.StudentsProgram;

public interface StudentsProgramService {
	public List<StudentsProgram> getStudentsProgramList();
	public StudentsProgram addStudentsProgram(StudentsProgram studentsProgram);
	public List<StudentsProgram> getStudentsProgram(int s_Id);
	public StudentsProgram updateStudentsProgram(StudentsProgram studentsProgram);
	public int deleteStudentsProgram(int s_Id);
	public List<StudentsProgram> getStudentsProgramByStudentId(int s_Id);
	public List<StudentsProgram> getStudentsProgramByProgramId(int s_Id);
	public List<StudentsProgram> searchByField(Object[] obj);
	
}
