package com.project.exam.dao;

import java.util.List;

import com.project.exam.model.ForgetPasswordModel;



public interface ForgetPassword {

	// for requesetion password change
	
	public ForgetPasswordModel forgetPasswordCheckEmail(String email);
	
	public boolean setAuthincatedCode(String tableName,String tableIdColumnName,int idOfUser);
	
	public boolean isEmailValid(String email);
	
	// for reset password below two method
	
	public boolean isAuthenticated(String tableName, int idOfUser, String vcode);
	
	public boolean resetPassword(String tableName, int idOfUser, String verificationCode, String password);
	
}
