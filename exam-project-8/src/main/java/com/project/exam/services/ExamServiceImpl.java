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
	public int addExam(Exam exam) {
		return examDao.addExam(exam);
	}

	@Override
	public List<Exam> getExam(int s_Id) {
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
	public List<Exam> searchByField(int examTypeId,int subjectId) {
		return examDao.searchByField(examTypeId,subjectId);
	}

	@Override
	public List<Exam> searchByField(int examTypeId, int programId, int batchYear, int semesterNo) {
		return examDao.searchByField(examTypeId, programId, batchYear, semesterNo);
	}

	@Override
	public List getExamRoutine(int s_Id) {
		return examDao.getExamRoutine(s_Id);
	}

}
