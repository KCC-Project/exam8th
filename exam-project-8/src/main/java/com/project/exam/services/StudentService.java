package com.project.exam.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Component
public interface StudentService {
	
	public List<Student> getStudentList();
	public Student addStudent(Student student);
	public List<Student> getStudent(int s_Id);
	public Student updateStudent(Student student);
	public int deleteStudent(int s_Id);
	public List searchStudent(String searchPara);
	public List getStudentsByStudentsProgram(Object[] obj);
}
