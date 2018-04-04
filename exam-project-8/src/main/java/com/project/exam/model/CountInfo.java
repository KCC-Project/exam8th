package com.project.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity(name="count_info")
@Immutable
public class CountInfo {
	
	@Column
	private int student_count;

	@Column
	private int subject_count;
	
	@Column
	private int program_count;
	
	@Column
	private int faculty_count;
	
	
	@Column
	private int exam_count;


	public int getStudent_count() {
		return student_count;
	}


	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}


	public int getSubject_count() {
		return subject_count;
	}


	public void setSubject_count(int subject_count) {
		this.subject_count = subject_count;
	}


	public int getProgram_count() {
		return program_count;
	}


	public void setProgram_count(int program_count) {
		this.program_count = program_count;
	}


	public int getFaculty_count() {
		return faculty_count;
	}


	public void setFaculty_count(int faculty_count) {
		this.faculty_count = faculty_count;
	}


	public int getExam_count() {
		return exam_count;
	}


	public void setExam_count(int exam_count) {
		this.exam_count = exam_count;
	}
	

	
	
	
}