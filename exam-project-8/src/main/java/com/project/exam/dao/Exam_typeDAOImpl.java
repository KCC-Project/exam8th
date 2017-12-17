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

import com.project.exam.model.Exam_type;

@Repository("exam_TypeDao")
public class Exam_typeDAOImpl implements Exam_typeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<Exam_type> getExam_typeList() {
		session = sessionFactory.getCurrentSession();
		List<Exam_type> listExam_type = session.createCriteria(Exam_type.class).list();
		return listExam_type;
	}

	@Override
	@Transactional
	public Exam_type addExam_type(Exam_type exam_type) {
		session = sessionFactory.getCurrentSession();
		session.save(exam_type);
		return exam_type;
	}

	@Override
	@Transactional
	public List<Exam_type> getExam_type(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Exam_type where exam_type_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<Exam_type> examTypeList = query.getResultList();
		return examTypeList;
	}

	@Override
	@Transactional
	public Exam_type updateExam_type(Exam_type exam_type) {
		session = sessionFactory.getCurrentSession();
		session.update(exam_type);
		return exam_type;
	}

	@Override
	@Transactional
	public int deleteExam_type(int s_Id) {
		session = sessionFactory.getCurrentSession();
		Exam_type examType = session.get(Exam_type.class, s_Id);
		session.delete(examType);
		return 1;
	}

	

	@Override
	@Transactional
	public List searchExamType(String searchPara) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Exam_type where type_name like '" + searchPara + "%'";
		Query query = session.createQuery(hql);
		List<Exam_type> examtypeList = query.getResultList();
		List listOfReslut = new ArrayList<>();
		for (Exam_type examtypeList1 : examtypeList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", examtypeList1.getExam_type_id());
			map.put("name", examtypeList1.getType_name());
			listOfReslut.add(map);
		}

		return listOfReslut;
	}


}
