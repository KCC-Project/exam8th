package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.StudentsExamDAO;
import com.project.exam.model.StudentsExam;

@Service
public class StudentsExamServiceImpl implements StudentsExamService {

	@Autowired
	StudentsExamDAO studentExamDao;
	@Override
	public List<StudentsExam> getstudentsExam() {
	return studentExamDao.getstudentsExamList();
	}

	@Override
	public int addstudentsExam(StudentsExam studentsExam) {
		// TODO Auto-generated method stub
		return studentExamDao.addstudentsExam(studentsExam);
	}

	@Override
	public List<StudentsExam> getstudentsExam(int s_Id) {
		// TODO Auto-generated method stub
		return studentExamDao.getstudentsExam(s_Id);
	}

	@Override
	public StudentsExam updatestudentsExam(StudentsExam studentsExam) {
		// TODO Auto-generated method stub
		return studentExamDao.updatestudentsExam(studentsExam);
	}

	@Override
	public int deletestudentsExam(int s_Id) {
		// TODO Auto-generated method stub
		return studentExamDao.deletestudentsExam(s_Id);
	}

	@Override
	public void getRequiredInfoTOSave(int a_program_id, int examTypeId, int semester_no) {
		studentExamDao.getRequiredInfoTOSave(a_program_id, examTypeId, semester_no);
		
	}

	@Override
	public List updatestudentExamModel(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName) {
		return studentExamDao.updatestudentExamModel(semesterNo, programeName, programId, batchyear, examTypeName, subjectId, examtypeId, subjectName);
		
	}

	@Override
	public List<StudentsExam> searchByField(int studentId) {
	return studentExamDao.searchByField(studentId);
	}

	@Override
	public List<StudentsExam> getstudentsExam(int s_Id, int semesterNo) {
		return studentExamDao.getstudentsExam(s_Id, semesterNo);
	}

	@Override
	public List getstudentsExam(int semesterNo, String programeName, int programId, int batchyear, String examTypeName,
			int subjectId, int examtypeId, String subjectName) {
		return studentExamDao.getstudentsExam(semesterNo, programeName, programId, batchyear, examTypeName, subjectId, examtypeId, subjectName);
	}

	
}
