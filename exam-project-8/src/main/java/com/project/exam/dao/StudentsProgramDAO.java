package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.StudentsProgram;

public interface StudentsProgramDAO {
	public List<StudentsProgram> getStudentsProgramList();
	public StudentsProgram addStudentsProgram(StudentsProgram studentsProgram);
	public StudentsProgram getStudentsProgram(int s_Id);
	public StudentsProgram updateStudentsProgram(StudentsProgram studentsProgram);
	public int deleteStudentsProgram(int s_Id);
	public List<StudentsProgram> getStudentsProgramByProgramId(int s_Id);
	public List<StudentsProgram> searchByField(Object[] obj);
	public void saveStudentProgram(int programID,int batch,String enrollDate);
	StudentsProgram getStudentsProgramByStudentId(int s_Id);
}
