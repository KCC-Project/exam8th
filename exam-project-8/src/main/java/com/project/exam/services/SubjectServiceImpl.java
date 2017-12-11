package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.SubjectDAO;
import com.project.exam.model.Subjects;
@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDAO subjectDao;
	
	@Override
	public List<Subjects> getallSubjectList() {
		return subjectDao.getallSubjectList();
	}

	@Override
	public Subjects addSubject(Subjects subject) {
		return subjectDao.addStudent(subject);
	}

	@Override
	public List<Subjects> getSubject(int s_Id) {
		return subjectDao.getSubject(s_Id);
	}

	@Override
	public Subjects updateSubject(Subjects subject) {
		return subjectDao.updateSubject(subject);
	}

	@Override
	public int deleteSubject(int s_Id) {
		return subjectDao.deleteSubject(s_Id);
	}

	@Override
	public List searchSubject(String searchPara) {
		return subjectDao.searchSubject(searchPara);
	}

	@Override
	public List<Subjects> getSubjectByParameters(Object[] obj) {
		return subjectDao.getSubjectByParameters(obj);
	}

	@Override
	public List getSubjectByProgram(Object[] obj) {
		return subjectDao.getSubjectByProgram(obj);
	}

}
