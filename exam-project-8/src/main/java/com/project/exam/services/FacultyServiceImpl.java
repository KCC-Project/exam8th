package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.FacultyDAO;
import com.project.exam.model.Faculty;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	private FacultyDAO facultyDao;
	
	@Override
	public List<Faculty> getFacultyList() {
	return facultyDao.getFacultyList();
	}

	@Override
	public Faculty addFaculty(Faculty faculty) {
		return facultyDao.addFaculty(faculty);
	}

	@Override
	public Faculty getFaculty(int s_Id) {
		return facultyDao.getFaculty(s_Id);
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {
		return facultyDao.updateFaculty(faculty);
	}

	@Override
	public int deleteFaculty(int s_Id) {
		return facultyDao.deleteFaculty(s_Id);
	}

}
