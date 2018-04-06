package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Check;

@XmlRootElement
@Entity
@Table(name="student_exam")

public class StudentsExam {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int students_exams_id;

	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name="exam_id")
	private Exam exam;

	// additional field
	private int attendance_status;
	private int obtained_marks;
	private String grade;
	private int status;
	public StudentsExam() {
		
	}
	
	public int getStudents_exams_id() {
		return students_exams_id;
	}
	public void setStudents_exams_id(int students_exams_id) {
		this.students_exams_id = students_exams_id;
	}
	
	public int getAttendance_status() {
		return attendance_status;
	}
	public void setAttendance_status(int attendance_status) {
		this.attendance_status = attendance_status;
	}
	public int getObtained_marks() {
		return obtained_marks;
	}
	public void setObtained_marks(int obtained_marks) {
		this.obtained_marks = obtained_marks;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	@Override
	public String toString() {
		return "StudentsExam [students_exams_id=" + students_exams_id + ", attendance_status=" + attendance_status
				+ ", obtained_marks=" + obtained_marks + ", grade=" + grade + ", status=" + status + "]";
	}
	
	
	

}
