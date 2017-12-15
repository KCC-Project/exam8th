package com.project.exam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.project.exam.model.Admin;
import com.project.exam.model.Program;
import com.project.exam.model.Student;

@Repository("programDao")
public class ProgramDAOImpl implements ProgramDAO {


	@Autowired
	private SessionFactory sessionFactory;
	private Session session=null;
	
	@Override
	@Transactional
	public List<Program> getProgramList() {
		session=sessionFactory.getCurrentSession();
		List<Program> listProgram=session.createCriteria(Program.class).list();
		return listProgram;
		
	}

	@Override
	@Transactional
	public Program addProgram(Program program) {
		session = sessionFactory.getCurrentSession();
		session.save(program);
		return program;
	}

	@Override
	@Transactional
	public List<Program> getProgram(int id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Program where program_id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<Program> programList = query.getResultList();
		return programList;
	}

	@Override
	@Transactional
	public Program updateProgram(Program program) {
		session = sessionFactory.getCurrentSession();
		session.update(program);
		return program;
	}

	@Override
	@Transactional
	public int deleteProgram(int id) {
		session = sessionFactory.getCurrentSession();
		Program program = session.get(Program.class, id);
		session.delete(program);
		return 1;
	}

	@Override
	@Transactional
	public List<Program> getProgramListByFacultyId(int id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Program where faculty = '" + id + "'";
		Query query = session.createQuery(hql);
		List<Program> programList = query.getResultList();
		return programList;
	}

	@Override
	@Transactional
	public List searchProgram(String searchPara) {
		List listOfReslut = new ArrayList<>();
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Program WHERE program_name like '"+searchPara+"%'";
		Query query = session.createQuery(hql);
		List<Program> programList = query.getResultList();
		for (Program program : programList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", program.getProgram_id());
			map.put("name", program.getProgram_name());			
			listOfReslut.add(map);
		}
		return listOfReslut;

}
	}
