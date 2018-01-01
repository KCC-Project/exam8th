package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.StudentsProgramDAO;
import com.project.exam.model.StudentsProgram;

@Service
public class StudentsProgramServiceImpl implements StudentsProgramService {
	@Autowired
	private StudentsProgramDAO studentsProgramDao;
	
	@Override
	public List<StudentsProgram> getStudentsProgramList() {
	return studentsProgramDao.getStudentsProgramList();
	}

	@Override
	public StudentsProgram addStudentsProgram(StudentsProgram studentsProgram) {
		return studentsProgramDao.addStudentsProgram(studentsProgram);
	}

	@Override
	public List<StudentsProgram> getStudentsProgram(int s_Id) {
		return studentsProgramDao.getStudentsProgram(s_Id);
	}

	@Override
	public StudentsProgram updateStudentsProgram(StudentsProgram studentsProgram) {
		return studentsProgramDao.updateStudentsProgram(studentsProgram);
	}

	@Override
	public int deleteStudentsProgram(int s_Id) {
		return studentsProgramDao.deleteStudentsProgram(s_Id);
	}


	@Override
	public List<StudentsProgram> getStudentsProgramByStudentId(int s_Id) {
		return studentsProgramDao.getStudentsProgramByStudentId(s_Id);
	}

	@Override
	public List<StudentsProgram> getStudentsProgramByProgramId(int s_Id) {
		return studentsProgramDao.getStudentsProgramByProgramId(s_Id);
	}

	

}
