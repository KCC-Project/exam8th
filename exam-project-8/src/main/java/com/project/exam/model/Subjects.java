package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subjects {

	private int subject_id;
	private int semester_no;
	private String subject_name;
	private String subject_code;
	private int theory_cr;
	private int tutorial_cr;
	private int internal_theory;
	private int internal_practical;
	private int final_theory;
	private String syllabus_file;
	private int status;
	private int program_id;

	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subjects(int subject_id, int semester_no, String subject_name, String subject_code, int theory_cr,
			int tutorial_cr, int internal_theory, int internal_practical, int final_theory, String syllabus_file,
			int status, int program_id) {
		super();
		this.subject_id = subject_id;
		this.semester_no = semester_no;
		this.subject_name = subject_name;
		this.subject_code = subject_code;
		this.theory_cr = theory_cr;
		this.tutorial_cr = tutorial_cr;
		this.internal_theory = internal_theory;
		this.internal_practical = internal_practical;
		this.final_theory = final_theory;
		this.syllabus_file = syllabus_file;
		this.status = status;
		this.program_id = program_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getSemester_no() {
		return semester_no;
	}

	public void setSemester_no(int semester_no) {
		this.semester_no = semester_no;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}

	public int getTheory_cr() {
		return theory_cr;
	}

	public void setTheory_cr(int theory_cr) {
		this.theory_cr = theory_cr;
	}

	public int getTutorial_cr() {
		return tutorial_cr;
	}

	public void setTutorial_cr(int tutorial_cr) {
		this.tutorial_cr = tutorial_cr;
	}

	public int getInternal_theory() {
		return internal_theory;
	}

	public void setInternal_theory(int internal_theory) {
		this.internal_theory = internal_theory;
	}

	public int getInternal_practical() {
		return internal_practical;
	}

	public void setInternal_practical(int internal_practical) {
		this.internal_practical = internal_practical;
	}

	public int getFinal_theory() {
		return final_theory;
	}

	public void setFinal_theory(int final_theory) {
		this.final_theory = final_theory;
	}

	public String getSyllabus_file() {
		return syllabus_file;
	}

	public void setSyllabus_file(String syllabus_file) {
		this.syllabus_file = syllabus_file;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}

	@Override
	public String toString() {
		return "Subjects [subject_id=" + subject_id + ", semester_no=" + semester_no + ", subject_name=" + subject_name
				+ ", subject_code=" + subject_code + ", theory_cr=" + theory_cr + ", tutorial_cr=" + tutorial_cr
				+ ", internal_theory=" + internal_theory + ", internal_practical=" + internal_practical
				+ ", final_theory=" + final_theory + ", syllabus_file=" + syllabus_file + ", status=" + status
				+ ", program_id=" + program_id + "]";
	}

}
