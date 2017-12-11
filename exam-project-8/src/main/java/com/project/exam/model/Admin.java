package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity	
@Table(name="admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admin_id;
	
	private String admin_username;
	private String admin_password;
	private int status;
	
	public Admin() {
	}
	public Admin(String admin_username, String admin_password, int status) {
		this.admin_username = admin_username;
		this.admin_password = admin_password;
		this.status = status;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_username() {
		return admin_username;
	}
	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_username=" + admin_username + ", admin_password="
				+ admin_password + ", status=" + status + "]";
	}

	
}
