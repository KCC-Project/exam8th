package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.ForgetPassword;
import com.project.exam.model.ForgetPasswordModel;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

	@Autowired
	private ForgetPassword forgetPassword;
	
	@Override
	public ForgetPasswordModel forgetPasswordCheckEmail(String email) {
		return forgetPassword.forgetPasswordCheckEmail(email);
	}

	@Override
	public boolean setAuthincatedCode(String tableName, String tableIdColumnName, int idOfUser) {
		return forgetPassword.setAuthincatedCode(tableName, tableIdColumnName, idOfUser);
	}

	@Override
	public boolean isEmailValid(String email) {
		return forgetPassword.isEmailValid(email);
	}

	@Override
	public boolean isAuthenticated(String tableName, int idOfUser, String vcode) {
		return forgetPassword.isAuthenticated(tableName, idOfUser, vcode);
	}

	@Override
	public boolean resetPassword(String tableName, int idOfUser, String verificationCode, String password) {
		return forgetPassword.resetPassword(tableName, idOfUser, verificationCode, password);
	}

}
