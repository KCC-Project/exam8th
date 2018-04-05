package com.project.exam.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Component
public interface StudentService {
	
	public List<Student> getStudentList();
	public int addStudent(Student student);
	public List<Student> getStudent(int s_Id);
	public List<Student> getStudent(String email);
	public Student updateStudent(Student student);
	public int deleteStudent(int s_Id);
	public List searchStudent(String searchPara);

	public List<Student> search(int id,int year);
	
	
	
	public String UpdateStudentsSemester(int program_id, int batch_year, int increment_value);
}
