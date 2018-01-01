package com.project.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Subjects;

@Repository("subjectDao")
public class SubjectDAOImpl implements SubjectDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session=null;
	
	@Override
	@Transactional
	public List<Subjects> getallSubjectList() {
		session=sessionFactory.getCurrentSession();
		List<Subjects> listSubjects=session.createCriteria(Subjects.class).list();
		return listSubjects;
	}

	@Override
	@Transactional
	public Subjects addStudent(Subjects subject) {
		session = sessionFactory.getCurrentSession();
		session.save(subject);
		return subject;
	}

	@Override
	@Transactional
	public List<Subjects> getSubject(int id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Subjects where subject_id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<Subjects> subjectsList = query.getResultList();
		return subjectsList;
	}

	@Override
	@Transactional
	public Subjects updateSubject(Subjects subject) {
		session = sessionFactory.getCurrentSession();
		session.update(subject);
		return subject;
	}

	@Override
	public int deleteSubject(int id) {
		session = sessionFactory.getCurrentSession();
		Subjects subjects = session.get(Subjects.class, id);
		session.delete(subjects);
		return 1;
	}

	@Override
	@Transactional
	public List searchSubject(String searchPara) {
		List listOfReslut = new ArrayList<>();
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Subjects WHERE subject_name like '"+searchPara+"%'";
		Query query = session.createQuery(hql);
		List<Subjects> subjectsList = query.getResultList();
		for (Subjects subject : subjectsList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", subject.getSubject_id());
			map.put("name", subject.getSubject_name());			
			listOfReslut.add(map);
		}
		return listOfReslut;
	}

	@Override
	@Transactional
	public List<Subjects> getSubjectByParameters(int programId,int semester) {
		session = sessionFactory.getCurrentSession();
		List<Subjects> listSubject = session.createCriteria(Subjects.class).add(Restrictions.eq("program.program_id", programId)).add(Restrictions.eq("semester_no",semester)).list();
		List subjectList = new ArrayList<>();
		for (Subjects subjectList1 : listSubject) {
			Subjects s=(Subjects)session.get(Subjects.class, subjectList1.getSubject_id());
			subjectList.add(s);
		}	
		return subjectList;

	}

	@Override
	@Transactional
	public List<Subjects> getSubjectByProgram(int programId) {
		session = sessionFactory.getCurrentSession();
		List<Subjects> listSubject = session.createCriteria(Subjects.class).add(Restrictions.eq("program.program_id", programId)).list();
		return listSubject;
	}
	

}
