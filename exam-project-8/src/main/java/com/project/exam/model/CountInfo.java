package com.project.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private String id;
	
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


	@Override
	public String toString() {
		return "CountInfo [id=" + id + ", student_count=" + student_count + ", subject_count=" + subject_count
				+ ", program_count=" + program_count + ", faculty_count=" + faculty_count + ", exam_count=" + exam_count
				+ "]";
	}
	
	
}