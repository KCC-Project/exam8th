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
	
	private String father_name;
	private String mother_name;
	
	private String f_phone;     // father phone no.
	private String m_phone;		// mother phone no.
	private String o_contact;	// Optional, Relatives/close By person phone number
	
	// primary contact refers to either father_contact or mother_contact or o_contact, with value 0 or 1
	private int primary_contact; 
	
	private int status;
	

	@ManyToOne
	@JoinColumn(name="s_id", nullable=false)
	private Student student_id;
	
	public StudentsParentInfo() {
		
	}

	public int getSp_info_id() {
		return sp_info_id;
	}

	public void setSp_info_id(int sp_info_id) {
		this.sp_info_id = sp_info_id;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getF_phone() {
		return f_phone;
	}

	public void setF_phone(String f_phone) {
		this.f_phone = f_phone;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public int getPrimary_contact() {
		return primary_contact;
	}

	public void setPrimary_contact(int primary_contact) {
		this.primary_contact = primary_contact;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "StudentsParentInfo [sp_info_id=" + sp_info_id + ", father_name=" + father_name + ", mother_name="
				+ mother_name + ", f_phone=" + f_phone + ", m_phone=" + m_phone + ", o_contact=" + o_contact
				+ ", primary_contact=" + primary_contact + ", status=" + status + ", student_id=" + student_id + "]";
	}
	
	
}
