package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.Exam;
import com.project.exam.model.StudentsProgram;

public interface ExamDAO {
	public List<Exam> getExamList();
	public int addExam(Exam exam);
	public List<Exam> getExam(int s_Id);
	public Exam updateExam(Exam exam);
	public int deleteExam(int s_Id);
	public List<Exam> searchByField(int examTypeId,int subjectId);
}
