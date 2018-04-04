package com.project.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Admin;
import com.project.exam.util.MD5Hash;

@Repository("adminDao")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;

	@Override
	@Transactional
	public List<Admin> getAdminList() {
		session = sessionFactory.getCurrentSession();
		List<Admin> listAdmin = session.createCriteria(Admin.class).list();
		return listAdmin;
	}

	@Override
	@Transactional
	public Admin addAdmin(Admin admin) {
		session = sessionFactory.getCurrentSession();
		//admin.setPassword(MD5Hash.MD5(admin.getPassword()));
		session.save(admin);
		return admin;
	}

	@Override
	@Transactional
	public List<Admin> getAdmin(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Admin where admin_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<Admin> adminList = query.getResultList();
		return adminList;
	}

	@Override
	@Transactional
	public Admin updateAdmin(Admin admin) {
		session = sessionFactory.getCurrentSession();
		session.update(admin);
		return admin;
	}

	@Override
	@Transactional
	public int deleteAdmin(int s_Id) {
		session = sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, s_Id);
		session.delete(admin);
		return 1;
	}

	@Override
	@Transactional
	public List searchAdmin(String searchPara) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Admin where admin_username like '" + searchPara + "%'";
		Query query = session.createQuery(hql);
		List<Admin> adminList = query.getResultList();
		List listOfReslut = new ArrayList<>();
		for (Admin admin : adminList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", admin.getAdmin_id());
			map.put("name", admin.getAdmin_username());
			listOfReslut.add(map);
		}

		return listOfReslut;
	}

}
