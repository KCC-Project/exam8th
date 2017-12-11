package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.Exam;
import com.project.exam.model.StudentsProgram;

public interface ExamDAO {
	public List<Exam> getExamList();
	public Exam addExam(Exam exam);
	public Exam getExam(int s_Id);
	public Exam updateExam(Exam exam);
	public int deleteExam(int s_Id);
	public List<Exam> searchByField(Object[] obj);
}
