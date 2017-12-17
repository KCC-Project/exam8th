package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="exam_type")
public class Exam_type {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int exam_type_id;
	
	private int status;
	private String type_name;

	public Exam_type() {
	
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
