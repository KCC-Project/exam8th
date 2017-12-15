package com.project.exam.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Faculty;

@Repository("FacultyDao")
public class FacultyDAOImpl implements FacultyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<Faculty> getFacultyList() {
		session = sessionFactory.getCurrentSession();
		List<Faculty> listFaculty = session.createCriteria(Faculty.class).list();
		return listFaculty;
	}

	@Override
	@Transactional
	public Faculty addFaculty(Faculty faculty) {
		session = sessionFactory.getCurrentSession();
		session.save(faculty);
		return faculty;
	}

	@Override
	@Transactional
	public List<Faculty> getFaculty(int id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Faculty where faculty_id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<Faculty> facultyList = query.getResultList();
		return facultyList;
	}

	@Override
	@Transactional
	public Faculty updateFaculty(Faculty faculty) {
		session = sessionFactory.getCurrentSession();
		session.update(faculty);
		return faculty;
	}

	@Override
	@Transactional
	public int deleteFaculty(int id) {
		session = sessionFactory.getCurrentSession();
		Faculty faculty = session.get(Faculty.class, id);
		session.delete(faculty);
		return 1;
	}
}
