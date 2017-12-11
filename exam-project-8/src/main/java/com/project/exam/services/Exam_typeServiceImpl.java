package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.Exam_typeDAO;
import com.project.exam.model.Exam_type;

@Service
public class Exam_typeServiceImpl implements Exam_typeService {
	@Autowired
	private Exam_typeDAO exam_typeDao;
	
	@Override
	public List<Exam_type> getExam_typeList() {
	return exam_typeDao.getExam_typeList();
	}

	@Override
	public Exam_type addExam_type(Exam_type Exam_type) {
		return exam_typeDao.addExam_type(Exam_type);
	}

	@Override
	public List<Exam_type> getExam_type(int s_Id) {
		return exam_typeDao.getExam_type(s_Id);
	}

	@Override
	public Exam_type updateExam_type(Exam_type Exam_type) {
		return exam_typeDao.updateExam_type(Exam_type);
	}

	@Override
	public int deleteExam_type(int s_Id) {
		return exam_typeDao.deleteExam_type(s_Id);
	}

	@Override
	public List searchExamType(String searchPara) {
		return exam_typeDao.searchExamType(searchPara);
	}


}
