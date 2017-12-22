package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "studentProgram")
public class StudentsProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int student_program_id;

	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name="program_id")
	private Program program;

	private int batch_year;

	private String enroll_date;

	private int status;

	public StudentsProgram() {
		
	}
	
	public int getStudent_program_id() {
		return student_program_id;
	}

	public void setStudent_program_id(int student_program_id) {
		this.student_program_id = student_program_id;
	}


	public int getBatch_year() {
		return batch_year;
	}

	public void setBatch_year(int batch_year) {
		this.batch_year = batch_year;
	}

	public String getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
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


	public Program getProgram() {
		return program;
	}


	public void setProgram(Program program) {
		this.program = program;
	}


	@Override
	public String toString() {
		return "StudentsProgram [student_program_id=" + student_program_id + ", batch_year=" + batch_year
				+ ", enroll_date=" + enroll_date + ", status=" + status + "]";
	}

}
