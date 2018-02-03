package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.Student;


public interface StudentDAO {
	public List<Student> getStudentList();
	public int addStudent(Student student);
	public List<Student> getStudent(int s_Id);
	public List<Student> getStudent(String email);
	public Student updateStudent(Student student);
	public int deleteStudent(int s_Id);
	public List searchStudent(String searchPara);
	public List<Student> search(int id,int year);
}
