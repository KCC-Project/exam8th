package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="program")
public class Program {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int program_id;
	
	private String program_name;
	
	private int program_years;

	
	private int status;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="faculty_id", nullable=false)
	private Faculty faculty;
	
	public Program() {
		
	}

	
	public Faculty getFaculty() {
		return faculty;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}


	public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public int getProgram_years() {
		return program_years;
	}

	public void setProgram_years(int program_years) {
		this.program_years = program_years;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Program [program_id=" + program_id + ", program_name=" + program_name + ", program_years="
				+ program_years + ", status=" + status + "]";
	}


	
	
	 
}
