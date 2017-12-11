package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.ProgramDAO;
import com.project.exam.model.Program;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramDAO programDao;
	
	public List<Program> getProgramList() {
		return programDao.getProgramList();
	}

	@Override
	public Program addProgram(Program program) {
		return programDao.addProgram(program);
	}

	@Override
	public List<Program> getProgram(int s_Id) {
		return programDao.getProgram(s_Id);
	}

	@Override
	public Program updateProgram(Program program) {
		return programDao.updateProgram(program);
	}

	@Override
	public int deleteProgram(int s_Id) {
		return programDao.deleteProgram(s_Id);
	}

	@Override
	public List<Program> getProgramListByFacultyId(int s_Id) {
		return programDao.getProgramListByFacultyId(s_Id);
	}

	@Override
	public List searchProgram(String searchPara) {
	return programDao.searchProgram(searchPara);
	}

}
