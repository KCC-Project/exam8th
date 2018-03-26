package com.project.exam.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;import com.project.exam.model.Student;
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
		
		String hql = "FROM StudentsParentInfo where student_id.s_id = '" + tudentsParentInfo.getStudent_id().getS_id() + "' And primary_contact='"+tudentsParentInfo.getPrimary_contact()+"'";
		
		Query query = session.createQuery(hql);
		
		List<StudentsParentInfo> list = query.getResultList();
		
		if (list.size()>0) {
			
			System.out.println("*********  This request already exit  *********");
		
		}else {
		
			session.save(tudentsParentInfo);
		
		}
	
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
		String hql = "FROM StudentsParentInfo p  where p.primary_contact="+contact+" and p.student_id.s_id = '" + studentId + "' ";
		Query query = session.createQuery(hql);
		
		List<StudentsParentInfo> List = query.getResultList();
		return List;
	}

	@Override
	@Transactional
	public List<StudentsParentInfo> getStudentsParentInfoByStudent(int studentId) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsParentInfo p  where p.student_id.s_id = '" + studentId + "' ";
		Query query = session.createQuery(hql);
		
		List<StudentsParentInfo> List = query.getResultList();
		return List;
		
		//List<StudentsParentInfo> studentsParentInfo = session.createCriteria(StudentsParentInfo.class).add(Restrictions.eq("student_id.s_id", studentId)).list();
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

	@Override
	@Transactional
	public List getStudentsParentByStatus(int status) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsParentInfo where status = '" + status + "'";
		Query query = session.createQuery(hql);
		List<StudentsParentInfo> List = query.getResultList();
		return List;
	}

	@Override
	@Transactional
	public int updateStudentsParentInfoById(int id) {
		session = sessionFactory.getCurrentSession();
		StudentsParentInfo studentsParentInfo = session.get(StudentsParentInfo.class, id);
		studentsParentInfo.setStatus(1);
		session.update(studentsParentInfo);
		return 1;
	}

}
