package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.StudentDAO;
import com.project.exam.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentDao;

	@Override
	public List<Student> getStudentList() {
		return studentDao.getStudentList();
	}

	@Override
	public Student addStudent(Student student) {
		return studentDao.addStudent(student);
	}

	@Override
	public List<Student> getStudent(int s_Id) {
		return studentDao.getStudent(s_Id);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	@Override
	public int deleteStudent(int s_Id) {
		return studentDao.deleteStudent(s_Id);
	}

	@Override
	public List searchStudent(String searchPara) {
		return studentDao.searchStudent(searchPara);
	}

	@Override
	public List getStudentsByStudentsProgram(Object[] obj) {
		return studentDao.getStudentsByStudentsProgram(obj);
	}

	
}
