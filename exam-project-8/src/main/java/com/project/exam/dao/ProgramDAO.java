package com.project.exam.dao;

import java.util.List;


import com.project.exam.model.Program;

public interface ProgramDAO {
	public List<Program> getProgramList();
	public Program addProgram(Program program);
	public List<Program> getProgram(int s_Id);
	public Program updateProgram(Program program);
	public int deleteProgram(int s_Id);
	public List<Program> getProgramListByFacultyId(int s_Id);
	public List searchProgram(String searchPara);
}
