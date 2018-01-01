package com.project.exam.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.StudentsProgram;

@Repository("/studemtsProgramDao")
public class StudentsProgramDAOImpl implements StudentsProgramDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;

	@Override
	@Transactional
	public List<StudentsProgram> getStudentsProgramList() {
		session = sessionFactory.getCurrentSession();
		List<StudentsProgram> listStudentProgram = session.createCriteria(StudentsProgram.class).list();
		return listStudentProgram;
	}

	@Override
	@Transactional
	public StudentsProgram addStudentsProgram(StudentsProgram studentsProgram) {
		session = sessionFactory.getCurrentSession();
		session.save(studentsProgram);
		return studentsProgram;
	}

	@Override
	@Transactional
	public List<StudentsProgram> getStudentsProgram(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentProgram where student_program_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<StudentsProgram> studentProgramList = query.getResultList();
		return studentProgramList;
	}
	@Override
	@Transactional
	public List<StudentsProgram> getStudentsProgramByStudentId(int s_Id) {
		session = sessionFactory.getCurrentSession();
		List<StudentsProgram> listStudentProgram = session.createCriteria(StudentsProgram.class).add(Restrictions.eq("student.s_id", s_Id)).list();
		for (StudentsProgram studentsProgram : listStudentProgram) {
			System.out.println("list = "+studentsProgram);
		}
		return listStudentProgram;
	}
	@Override
	@Transactional
	public List<StudentsProgram> getStudentsProgramByProgramId(int s_Id) {
		session = sessionFactory.getCurrentSession();
		List<StudentsProgram> listStudentProgram = session.createCriteria(StudentsProgram.class).add(Restrictions.eq("program.program_id", s_Id)).list();
		return listStudentProgram;
	}
	@Override
	@Transactional
	public StudentsProgram updateStudentsProgram(StudentsProgram studentsProgram) {
		session = sessionFactory.getCurrentSession();
		session.update(studentsProgram);
		return studentsProgram;
	}

	@Override
	@Transactional
	public int deleteStudentsProgram(int s_Id) {
		session = sessionFactory.getCurrentSession();
		StudentsProgram stp = session.get(StudentsProgram.class, s_Id);
		session.delete(stp);
		return 1;
	}
}
