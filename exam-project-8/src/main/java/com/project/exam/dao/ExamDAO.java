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
	public List<Exam> searchByField(int examTypeId,int programId,int batchYear,int semesterNo);
	
	//this method is only used by parents mode only
	public List getExamRoutine(int s_Id);
	
	// this method returns exams -> WHERE DATE(exam_date) > DATE(NOW()) and status = 0
	List<Exam> getRunningExams();
}
