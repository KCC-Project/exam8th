package com.project.exam.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.FailRatioCountView;

@Repository("failRatioCountViewDao")
public class FailRatioCountViewImpl implements FailRatioCountViewDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;

	@Override
	@Transactional
	public List<FailRatioCountView> getAllCounts() {
		session = sessionFactory.getCurrentSession();
		List<FailRatioCountView> countFailRatioCountView = session.createCriteria(FailRatioCountView.class).list();
		return countFailRatioCountView;
	}

}
