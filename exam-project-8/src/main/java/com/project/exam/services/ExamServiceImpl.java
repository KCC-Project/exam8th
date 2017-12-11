package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.ExamDAO;
import com.project.exam.model.Exam;

@Service
public class ExamServiceImpl implements ExamService {


	@Autowired
	private ExamDAO examDao;
	
	@Override
	public List<Exam> getExamList() {
	return examDao.getExamList();
	}

	@Override
	public Exam addExam(Exam exam) {
		return examDao.addExam(exam);
	}

	@Override
	public Exam getExam(int s_Id) {
		return examDao.getExam(s_Id);
	}

	@Override
	public Exam updateExam(Exam exam) {
		return examDao.updateExam(exam);
	}

	@Override
	public int deleteExam(int s_Id) {
		return examDao.deleteExam(s_Id);
	}

	@Override
	public List<Exam> searchByField(Object[] obj) {
		return examDao.searchByField(obj);
	}

}
