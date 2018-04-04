package com.project.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Immutable;

@XmlRootElement
@Entity
@Table(name="count_info")
@Immutable
public class CountInfo {
	
	@Id
	private long id;
	
	@Column
	private long student_count;

	@Column
	private long subject_count;
	
	@Column
	private long program_count;
	
	@Column
	private long faculty_count;
	
	
	@Column
	private long exam_count;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getStudent_count() {
		return student_count;
	}


	public void setStudent_count(long student_count) {
		this.student_count = student_count;
	}


	public long getSubject_count() {
		return subject_count;
	}


	public void setSubject_count(long subject_count) {
		this.subject_count = subject_count;
	}


	public long getProgram_count() {
		return program_count;
	}


	public void setProgram_count(long program_count) {
		this.program_count = program_count;
	}


	public long getFaculty_count() {
		return faculty_count;
	}


	public void setFaculty_count(long faculty_count) {
		this.faculty_count = faculty_count;
	}


	public long getExam_count() {
		return exam_count;
	}


	public void setExam_count(long exam_count) {
		this.exam_count = exam_count;
	}


	@Override
	public String toString() {
		return "CountInfo [id=" + id + ", student_count=" + student_count + ", subject_count=" + subject_count
				+ ", program_count=" + program_count + ", faculty_count=" + faculty_count + ", exam_count=" + exam_count
				+ "]";
	}
	
	
}