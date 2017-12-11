package com.project.exam.services;

import java.util.List;

import com.project.exam.model.Exam;

public interface ExamService {
	public List<Exam> getExamList();
	public Exam addExam(Exam exam);
	public Exam getExam(int s_Id);
	public Exam updateExam(Exam exam);
	public int deleteExam(int s_Id);
	public List<Exam> searchByField(Object[] obj);
}
