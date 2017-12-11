package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exam_type {

	private int exam_type_id;
	private int status;
	private String type_name;

	public Exam_type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam_type(int exam_type_id, int status, String type_name) {
		super();
		this.exam_type_id = exam_type_id;
		this.status = status;
		this.type_name = type_name;
	}

	public int getExam_type_id() {
		return exam_type_id;
	}

	public void setExam_type_id(int exam_type_id) {
		this.exam_type_id = exam_type_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
