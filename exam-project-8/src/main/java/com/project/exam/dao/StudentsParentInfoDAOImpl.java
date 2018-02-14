package com.project.exam.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.StudentsParentInfo;

@Repository
public class StudentsParentInfoDAOImpl implements StudentsParentInfoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<StudentsParentInfo> getStudentsParentInfoList() {
		session = sessionFactory.getCurrentSession();
		List<StudentsParentInfo> list = session.createCriteria(StudentsParentInfo.class).list();
		return list;
	}

	@Override
	@Transactional
	public StudentsParentInfo addStudentsParentInfo(StudentsParentInfo tudentsParentInfo) {
		session = sessionFactory.getCurrentSession();
		session.save(tudentsParentInfo);
		return tudentsParentInfo;
	}

	@Override
	@Transactional
	public List<StudentsParentInfo> getStudentsParentInfo(int id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsParentInfo where sp_info_id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<StudentsParentInfo> List = query.getResultList();
		return List;
	}

	@Override
	@Transactional
	public List<StudentsParentInfo> getStudentsParentInfo(int studentId, String contact) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsParentInfo where StudentsParentInfo.Student.s_id = '" + studentId + "' And primary_contact='"+contact+"'";
		Query query = session.createQuery(hql);
		List<StudentsParentInfo> List = query.getResultList();
		return List;
	}

	@Override
	@Transactional
	public List<StudentsParentInfo> getStudentsParentInfoByStudent(int studentId) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsParentInfo where StudentsParentInfo.Student.s_id = '" + studentId + "' ";
		Query query = session.createQuery(hql);
		List<StudentsParentInfo> List = query.getResultList();
		return List;
	}

	@Override
	@Transactional
	public StudentsParentInfo updateStudentsParentInfo(StudentsParentInfo tudentsParentInfo) {
		session = sessionFactory.getCurrentSession();
		session.update(tudentsParentInfo);
		return tudentsParentInfo;
	}

	@Override
	@Transactional
	public int deleteStudentsParentInfo(int idd) {
		session = sessionFactory.getCurrentSession();
		StudentsParentInfo studentsParentInfo = session.get(StudentsParentInfo.class, idd);
		session.delete(studentsParentInfo);
		return 1;
	}

}
