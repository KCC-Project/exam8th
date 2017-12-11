package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.Faculty;

public interface FacultyDAO 
{
	public List<Faculty> getFacultyList();
	public Faculty addFaculty(Faculty faculty);
	public Faculty getFaculty(int s_Id);
	public Faculty updateFaculty(Faculty faculty);
	public int deleteFaculty(int s_Id);

}
