package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.StudentsParentInfo;

public interface StudentsParentInfoDAO {

	public List<StudentsParentInfo> getStudentsParentInfoList();

	public StudentsParentInfo addStudentsParentInfo(StudentsParentInfo tudentsParentInfo);

	public List<StudentsParentInfo> getStudentsParentInfo(int id);
	
	public List<StudentsParentInfo> getStudentsParentInfo(int studentId,String contact);
	
	public List<StudentsParentInfo> getStudentsParentInfoByStudent(int studentId);

	public StudentsParentInfo updateStudentsParentInfo(StudentsParentInfo tudentsParentInfo);

	public int deleteStudentsParentInfo(int idd);

	public List getStudentsParentByStatus(int status);
	
	public int updateStudentsParentInfoById(int id);
}
