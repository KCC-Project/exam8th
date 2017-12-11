package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentsExam {

	
	private int students_exams_id;

	private int s_id;

	private int exam_id;

	// additional field
	private int attendance_status;
	private int obtained_marks;
	private String grade;
	private int status;
	public StudentsExam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentsExam(int students_exams_id, int s_id, int exam_id, int attendance_status, int obtained_marks,
			String grade, int status) {
		super();
		this.students_exams_id = students_exams_id;
		this.s_id = s_id;
		this.exam_id = exam_id;
		this.attendance_status = attendance_status;
		this.obtained_marks = obtained_marks;
		this.grade = grade;
		this.status = status;
	}
	public int getStudents_exams_id() {
		return students_exams_id;
	}
	public void setStudents_exams_id(int students_exams_id) {
		this.students_exams_id = students_exams_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
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
	@Override
	public String toString() {
		return "StudentsExam [students_exams_id=" + students_exams_id + ", s_id=" + s_id + ", exam_id=" + exam_id
				+ ", attendance_status=" + attendance_status + ", obtained_marks=" + obtained_marks + ", grade=" + grade
				+ ", status=" + status + "]";
	}

	
	

}
