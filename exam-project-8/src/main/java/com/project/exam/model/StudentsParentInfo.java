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
@Table(name="students_parent_info")
public class StudentsParentInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sp_info_id;
	

	@ManyToOne
	@JoinColumn(name="s_id", nullable=false)
	private Student student_id;
	
	private String fullname;
	private String relation;
	private String primary_contact; 
	private int status;
	
	public StudentsParentInfo() {
	
	}

	public int getSp_info_id() {
		return sp_info_id;
	}

	public void setSp_info_id(int sp_info_id) {
		this.sp_info_id = sp_info_id;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPrimary_contact() {
		return primary_contact;
	}

	public void setPrimary_contact(String primary_contact) {
		this.primary_contact = primary_contact;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	


	
}
