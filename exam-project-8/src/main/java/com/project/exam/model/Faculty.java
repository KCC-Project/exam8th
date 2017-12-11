package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Faculty {

	
	private int faculty_id;
	
	private String faculty_name;
	
	private int status;

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faculty(int faculty_id, String faculty_name, int status) {
		super();
		this.faculty_id = faculty_id;
		this.faculty_name = faculty_name;
		this.status = status;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	public String getFaculty_name() {
		return faculty_name;
	}

	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Faculty [faculty_id=" + faculty_id + ", faculty_name=" + faculty_name + ", status=" + status + "]";
	}
	
	
	
}
