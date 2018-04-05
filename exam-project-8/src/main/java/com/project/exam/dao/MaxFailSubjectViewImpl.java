package com.project.exam.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.MaxFailSubjectView;

@Repository("maxFailFubjectView")
public class MaxFailSubjectViewImpl implements MaxFailSubjectViewDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;

	@Override
	@Transactional
	public List<MaxFailSubjectView> getAllRecords() {
		session = sessionFactory.getCurrentSession();
		List<MaxFailSubjectView> maxFailFubjectView = session.createCriteria(MaxFailSubjectView.class).list();
		return maxFailFubjectView;
	}

}
