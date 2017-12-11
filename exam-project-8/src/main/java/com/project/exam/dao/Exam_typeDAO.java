package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.Exam_type;

public interface Exam_typeDAO {
	public List<Exam_type> getExam_typeList();
	public Exam_type addExam_type(Exam_type exam_type);
	public List<Exam_type> getExam_type(int s_Id);
	public Exam_type updateExam_type(Exam_type exam_type);
	public int deleteExam_type(int s_Id);
	public List searchExamType(String searchPara);
}
