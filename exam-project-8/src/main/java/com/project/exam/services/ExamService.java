package com.project.exam.services;

import java.util.List;

import com.project.exam.model.Exam;

public interface ExamService {
	public List<Exam> getExamList();
	public int addExam(Exam exam);
	public List<Exam> getExam(int s_Id);
	public Exam updateExam(Exam exam);
	public int deleteExam(int s_Id);
	public List<Exam> searchByField(int examTypeId,int subjectId);
	public List<Exam> searchByField(int examTypeId,int programId,int batchYear,int semesterNo);
	
	//this method is only used by parents mode only
		public List getExamRoutine(int s_Id);
}
