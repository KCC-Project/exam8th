package com.project.exam.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentsProgram {
	
	private int student_program_id;

	private int s_id;

	private int program_id;

	private int batch_year;
	
	private String enroll_date;
	
	private int status;

	public StudentsProgram() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentsProgram(int student_program_id, int s_id, int program_id, int batch_year, String enroll_date,
			int status) {
		super();
		this.student_program_id = student_program_id;
		this.s_id = s_id;
		this.program_id = program_id;
		this.batch_year = batch_year;
		this.enroll_date = enroll_date;
		this.status = status;
	}

	public int getStudent_program_id() {
		return student_program_id;
	}

	public void setStudent_program_id(int student_program_id) {
		this.student_program_id = student_program_id;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
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

	@Override
	public String toString() {
		return "StudentsProgram [student_program_id=" + student_program_id + ", s_id=" + s_id + ", program_id="
				+ program_id + ", batch_year=" + batch_year + ", enroll_date=" + enroll_date + ", status=" + status
				+ "]";
	}
	

	

}
