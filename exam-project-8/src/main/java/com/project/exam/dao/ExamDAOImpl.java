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

import com.project.exam.model.Exam;

@Repository("examDao")
public class ExamDAOImpl implements ExamDAO {
	

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

	@Override
	@Transactional
	public List<Exam> searchByField(int examTypeId, int programId, int batchYear, int semesterNo) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT Distinct(sub.subject_name),sub.subject_id,et.exam_type_id,"
				+ "et.type_name,pro.program_id,pro.program_name,e.exam_id,e.exam_date,e.full_marks,e.pass_marks,"
				+ "e.status,e.time_from,e.time_to,sub.semester_no FROM exam e INNER JOIN exam_type et "
				+ "ON et.exam_type_id=e.exam_type_id INNER JOIN subjects sub "
				+ "ON sub.subject_id=e.subject_id INNER JOIN program pro "
				+ "ON pro.program_id=sub.program_id INNER JOIN studentprogram stupro "
				+ "ON stupro.program_id=pro.program_id WHERE et.exam_type_id="+examTypeId+" AND pro.program_id="+programId+" "
				+ "AND stupro.batch_year="+batchYear+" AND sub.semester_no="+semesterNo+"");
		List<Object[]>  result = query.getResultList();
		System.out.println("Result size = "+result.size());
		List list = new ArrayList<>();
		for (Object[] object : result) {
			Map<String, Object> map= new HashMap<>();
			
		map.put("subject_name", object[0]);
		map.put("subject_id", object[1]);
		map.put("exam_type_id", object[2]);
		map.put("type_name", object[3]);
		map.put("program_id", object[4]);
		map.put("program_name", object[5]);
		map.put("exam_id", object[6]);
		map.put("exam_date", object[7]);
		map.put("full_marks", object[8]);
		map.put("pass_marks", object[9]);
		map.put("status", object[10]);
		map.put("time_from", object[11]);
		map.put("time_to", object[12]);
		map.put("semester_no", object[13]);
			list.add(map);
		}
		return list;
	}
}