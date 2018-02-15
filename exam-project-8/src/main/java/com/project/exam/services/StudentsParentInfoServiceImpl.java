package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.StudentsParentInfoDAO;
import com.project.exam.model.StudentsParentInfo;

@Service
public class StudentsParentInfoServiceImpl implements StudentsParentInfoService {

	@Autowired
	StudentsParentInfoDAO studentParents;  
	
	@Override
	public List<StudentsParentInfo> getStudentsParentInfoList() {
		return studentParents.getStudentsParentInfoList();
	}

	@Override
	public StudentsParentInfo addStudentsParentInfo(StudentsParentInfo tudentsParentInfo) {
		return studentParents.addStudentsParentInfo(tudentsParentInfo);
	}

	@Override
	public List<StudentsParentInfo> getStudentsParentInfo(int id) {
		return studentParents.getStudentsParentInfo(id);
	}

	@Override
	public List<StudentsParentInfo> getStudentsParentInfo(int studentId, String contact) {
		return studentParents.getStudentsParentInfo(studentId, contact);
	}

	@Override
	public List<StudentsParentInfo> getStudentsParentInfoByStudent(int studentId) {
		return studentParents.getStudentsParentInfoByStudent(studentId);
		}

	@Override
	public StudentsParentInfo updateStudentsParentInfo(StudentsParentInfo tudentsParentInfo) {
		return studentParents.updateStudentsParentInfo(tudentsParentInfo);
	}

	@Override
	public int deleteStudentsParentInfo(int idd) {
		return studentParents.deleteStudentsParentInfo(idd);
	}

	@Override
	public List getStudentsParentByStatus(int status) {
		return studentParents.getStudentsParentByStatus(status);
	}

	@Override
	public int updateStudentsParentInfoById(int id) {
		return studentParents.updateStudentsParentInfoById(id);
	}

}
