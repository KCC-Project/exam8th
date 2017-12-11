package com.project.exam.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.exam.model.Faculty;

@Component
public interface FacultyService {
	public List<Faculty> getFacultyList();
	public Faculty addFaculty(Faculty faculty);
	public Faculty getFaculty(int s_Id);
	public Faculty updateFaculty(Faculty faculty);
	public int deleteFaculty(int s_Id);
}
