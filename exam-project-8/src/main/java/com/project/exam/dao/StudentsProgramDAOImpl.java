package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Admin;
import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Repository("/studemtsProgramDao")
public class StudentsProgramDAOImpl implements StudentsProgramDAO {

	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	
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



	@Override
	public List<StudentsProgram> searchByField(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List<StudentsProgram> studentsProgramModel = new ArrayList<>();

		int student_program_id = 0;
		int s_id = 0;
		int program_id = 0;
		int batch_year = 0;
		String enroll_date = null;
		int status = 0;

		if (obj[0] != null) {  student_program_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  s_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  program_id = Integer.parseInt(obj[2].toString()); }
		if (obj[3] != null) {  batch_year = Integer.parseInt(obj[3].toString()); }
		if (obj[4] != null) {  enroll_date = obj[4].toString(); }
		if (obj[5] != null) {  status = Integer.parseInt(obj[5].toString()); }
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM students_program WHERE 1=1");
			  
				if (student_program_id != 0) {
		            query.append(" AND student_program_id = ?");
		            parameters.add(student_program_id);
		        }
				if (s_id != 0) {
		            query.append(" AND s_id = ?");
		            parameters.add(s_id);
		        }
				if (program_id != 0) {
		            query.append(" AND program_id = ?");
		            parameters.add(program_id);
		        }
				if (batch_year != 0) {
		            query.append(" AND batch_year = ?");
		            parameters.add(batch_year);
		        }
				if (enroll_date != null) {
		            query.append(" AND enroll_date = ?");
		            parameters.add(enroll_date);
		        }
				if (status != 0) {
		            query.append(" AND status = ?");
		            parameters.add(status);
		        }
				  String Query = query.toString();
			        System.out.println(Query);
			        
			        conn = DatabaseConnection.connectToDatabase();
			        pst = conn.prepareStatement(Query);
			        
			        int i = 1;
			        for (Object parameter : parameters) {
			            pst.setObject(i++, parameter);
			        }
			        rs = pst.executeQuery();
			        if (rs != null) {
			        	
			        	while(rs.next()){
			        		StudentsProgram model = new StudentsProgram();
			        		
			        		model.setStudent_program_id(rs.getInt("student_program_id"));
							model.setBatch_year(rs.getInt("batch_year"));
							model.setEnroll_date(rs.getString("enroll_date"));
							model.setStatus(rs.getInt("status"));
							
							studentsProgramModel.add(model);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return studentsProgramModel;
	}

	

	

	

}
