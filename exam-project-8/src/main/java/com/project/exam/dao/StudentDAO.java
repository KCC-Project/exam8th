package com.project.exam.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.exam.model.Student;


public interface StudentDAO {
	public List<Student> getStudentList();
	public Student addStudent(Student student);
	public List<Student> getStudent(int s_Id);
	public Student updateStudent(Student student);
	public int deleteStudent(int s_Id);
	public List searchStudent(String searchPara);
	public List getStudentsByStudentsProgram(Object[] obj);

}
