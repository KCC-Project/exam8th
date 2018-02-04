package com.project.exam.model;

public class ForgetPasswordModel {

	private int idOfUser;
	private String emailOfUser;
	private String typeOfUser;
	private String authienciationCodeOfUser;
	
	public ForgetPasswordModel() {
	
	}

	public ForgetPasswordModel(int idOfUser, String emailOfUser, String typeOfUser, String authienciationCodeOfUser) {
		super();
		this.idOfUser = idOfUser;
		this.emailOfUser = emailOfUser;
		this.typeOfUser = typeOfUser;
		this.authienciationCodeOfUser = authienciationCodeOfUser;
	}

	public int getIdOfUser() {
		return idOfUser;
	}

	public void setIdOfUser(int idOfUser) {
		this.idOfUser = idOfUser;
	}

	public String getEmailOfUser() {
		return emailOfUser;
	}

	public void setEmailOfUser(String emailOfUser) {
		this.emailOfUser = emailOfUser;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getAuthienciationCodeOfUser() {
		return authienciationCodeOfUser;
	}

	public void setAuthienciationCodeOfUser(String authienciationCodeOfUser) {
		this.authienciationCodeOfUser = authienciationCodeOfUser;
	}
	
	
	
}
