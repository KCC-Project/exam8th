package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Admin;
import com.project.exam.model.Exam;
import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;
import com.project.exam.model.Subjects;

@Repository("examDao")
public class ExamDAOImpl implements ExamDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;


	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<Exam> getExamList() {
		session = sessionFactory.getCurrentSession();
	List<Exam> listExam= session.createCriteria(Exam.class).list();
	return listExam;
	}

	@Override
	@Transactional
	public int addExam(Exam exam) {
		session = sessionFactory.getCurrentSession();
		int examId=(Integer)session.save(exam);
		System.out.println("exam id after save is = "+examId);
			return examId;
	}

	@Override
	@Transactional
	public List<Exam> getExam(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Exam where exam_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<Exam> examList = query.getResultList();
		return examList;
	}

	@Override
	@Transactional
	public Exam updateExam(Exam exam) {
		session = sessionFactory.getCurrentSession();
	session.update(exam);
	return exam;
	}

	@Override
	@Transactional
	public int deleteExam(int s_Id) {
		session = sessionFactory.getCurrentSession();
		Exam exam = session.get(Exam.class, s_Id);
		session.delete(exam);
		return 1;
	}

	@Override
	@Transactional
	public List<Exam> searchByField(int examTypeId,int subjectId) {
		session = sessionFactory.getCurrentSession();
		List<Exam> listexam = session.createCriteria(Exam.class).add(Restrictions.eq("examtype.exam_type_id", examTypeId)).add(Restrictions.eq("subject.subject_id",subjectId)).list();
		
		return listexam;
		
			}
}