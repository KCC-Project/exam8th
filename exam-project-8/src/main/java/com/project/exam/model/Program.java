package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Program {

	
	private int program_id;
	
	private String program_name;
	
	private int program_years;

	
	private int status;
	
	private int faculty_id;

	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Program(int program_id, String program_name, int program_years, int status, int faculty_id) {
		super();
		this.program_id = program_id;
		this.program_name = program_name;
		this.program_years = program_years;
		this.status = status;
		this.faculty_id = faculty_id;
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

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	@Override
	public String toString() {
		return "Program [program_id=" + program_id + ", program_name=" + program_name + ", program_years="
				+ program_years + ", status=" + status + ", faculty_id=" + faculty_id + "]";
	}

	
	
	
	 
}
