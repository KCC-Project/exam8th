package com.project.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Immutable;

@XmlRootElement
@Entity
@Table(name="max_fail_subject_view  ")
@Immutable
public class MaxFailSubjectView {

	@Id
	private long id;
	
	@Column
	private long total_failed;
	
	@Column
	private String subject_name;
	
	@Column
	private long subject_id;
	
	@Column
	private String program_name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTotal_failed() {
		return total_failed;
	}

	public void setTotal_failed(long total_failed) {
		this.total_failed = total_failed;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	
	
	
}
