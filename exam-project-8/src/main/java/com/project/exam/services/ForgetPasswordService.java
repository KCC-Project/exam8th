package com.project.exam.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.exam.model.ForgetPasswordModel;

public interface ForgetPasswordService {

	public ForgetPasswordModel forgetPasswordCheckEmail(String email);

	public boolean setAuthincatedCode(String tableName, String tableIdColumnName, int idOfUser);

	public boolean isEmailValid(String email);

	public boolean isAuthenticated(String tableName, int idOfUser, String vcode);

	public boolean resetPassword(String tableName, int idOfUser, String verificationCode, String password);
}
