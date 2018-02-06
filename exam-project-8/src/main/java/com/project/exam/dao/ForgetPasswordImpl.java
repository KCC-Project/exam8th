package com.project.exam.dao;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Admin;
import com.project.exam.model.ForgetPasswordModel;
import com.project.exam.model.Student;

@Repository("forgetPasswordDao")
public class ForgetPasswordImpl implements ForgetPassword {

	private String tableName = null;

	private String emailField = null;

	private String idName = null;

	private String type = null;

	private int idValue = 0;

	private String hashCode = null;

	@Autowired
	private SessionFactory sessionFactory;

	Session session = null;

	@Override
	@Transactional
	public ForgetPasswordModel forgetPasswordCheckEmail(String email) {

		ForgetPasswordModel model = new ForgetPasswordModel();

		try {

			int tablecount = 2;
			do {
				if (tablecount == 2) {

					tableName = "Student";

					emailField = "email";

					idName = "s_id";

					type = "student";

					boolean validationChecked = isEmailValid(email);

					if (validationChecked) {

						setAuthincatedCode(tableName, idName, idValue);

						// setting forget password model for json activity over network

						model.setAuthienciationCodeOfUser(hashCode);

						model.setEmailOfUser(email);

						model.setIdOfUser(idValue);

						model.setTypeOfUser(tableName);

					}

					tablecount--;
				}

				else if (tablecount == 1) {

					tableName = "Admin";

					emailField = "email";

					idName = "admin_id";

					type = "admin";

					boolean validationChecked = isEmailValid(email);

					if (validationChecked) {

						System.out.println("inside Admin");

						setAuthincatedCode(tableName, idName, idValue);

					}
					tablecount--;
				}

			} while (tablecount > 0);

		} catch (Exception e) {

		}
		return model;
	}

	@Override
	@Transactional
	public boolean setAuthincatedCode(String tableName, String tableIdColumnName, int idOfUser) {

		session = sessionFactory.getCurrentSession();

		boolean updatedHash = false;

		//hashCode = UUID.randomUUID().toString();

		Random rand = new Random();
		
		hashCode = Integer.toString(rand.nextInt(20));
		
		String q = "update " + tableName + " set verificationCode = '" + hashCode + "' where " + idName + " = '" + idOfUser
				+ "'";

		Query query = session.createQuery(q);

		int result = query.executeUpdate();

		if (result > 0) {

			updatedHash = true;

		}

		return updatedHash;
	}

	@Override
	@Transactional
	public boolean isEmailValid(String email) {

		boolean valid = false;

		session = sessionFactory.getCurrentSession();

		String hql = "FROM " + tableName + " where email = '" + email + "'";

		Query query = session.createQuery(hql);

		if (tableName.equalsIgnoreCase("student")) {

			List<Student> resultList = query.getResultList();

			if (resultList.size() > 0) {

				for (Student student : resultList) {

					idValue = student.getS_id();

				}

				valid = true;
			}

		} else {

			List<Admin> resultList = query.getResultList();

			if (resultList.size() > 0) {

				for (Admin admin : resultList) {

					idValue = admin.getAdmin_id();
				}

				valid = true;
			}
		}

		return valid;

	}

	@Override
	@Transactional
	public boolean isAuthenticated(String tableName, int idOfUser, String vcode) {

		String tableColName = null;

		if (tableName.equalsIgnoreCase("student")) {

			tableColName = "s_id";

		} else if (tableName.equalsIgnoreCase("admin")) {

			tableColName = "admin_id";
		}

		session = sessionFactory.getCurrentSession();

		String hql = "FROM " + tableName + " where " + tableColName + " = '" + idOfUser + "' AND verificationCode='" + vcode
				+ "'";

		Query query = session.createQuery(hql);

		List result = query.getResultList();

		if (result.size() > 0) {

			return true;

		}

		return false;
	}

	@Override
	@Transactional
	public boolean resetPassword(String tableName, int idOfUser, String verificationCode, String password) {

		session = sessionFactory.getCurrentSession();
		
		String tableColName = null;

		if (tableName.equalsIgnoreCase("student")) {

			tableColName = "s_id";

		} else if (tableName.equalsIgnoreCase("admin")) {

			tableColName = "admin_id";
		}
		
		
		String q = "update " + tableName + " set password = '" + password + "' where " + tableColName + " = '" + idOfUser
				+ "'";

		Query query = session.createQuery(q);

		int result = query.executeUpdate();

		if (result > 0) {

			return true;

		}
		

		return false;
	}
}
