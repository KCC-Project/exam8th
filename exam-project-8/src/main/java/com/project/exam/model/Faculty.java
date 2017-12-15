package com.project.exam.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="faculty_id")
	private int faculty_id;
	
	private String faculty_name;
	
	private int status;

	/*@OneToMany(mappedBy="faculty",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Collection<Program> program=new ArrayList<Program>();
	*/

	public Faculty() {
	}

	/*
	public Collection<Program> getProgram() {
		return program;
	}

	public void setProgram(Collection<Program> program) {
		this.program = program;
	}

*/
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
