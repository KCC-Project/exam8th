package com.project.exam.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.CountInfo;
import com.project.exam.model.Faculty;

@Repository("countDao")
public class CountDaoImpl implements CountDao{

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;

	@Override
	@Transactional
	public List<CountInfo> getAllCounts() {
		session = sessionFactory.getCurrentSession();
		List<CountInfo> countInfo = session.createCriteria(CountInfo.class).list();
		System.out.println("List of countInfo = "+countInfo.toString());
		return countInfo;
	}


}
