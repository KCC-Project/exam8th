package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Subjects;

@Repository("subjectDao")
public class SubjectDAOImpl implements SubjectDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

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
	public List<Subjects> getSubjectByParameters(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List<Subjects> listSubjects = new ArrayList<Subjects>();

		int subject_id = 0;
		int program_id = 0;
		String subject_name = null;
		String subject_code = null;
		int theory_cr = 0;
		int tutorial_cr = 0;
		int internal_theory = 0;
		int internal_practical = 0;
		int final_theory = 0;
		int semester_no = 0;
		int status = 0;


		if (obj[0] != null) {  subject_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  program_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  subject_name = obj[2].toString(); }
		if (obj[3] != null) {  subject_code = obj[3].toString(); }
		if (obj[4] != null) {  theory_cr = Integer.parseInt(obj[4].toString()); }
		if (obj[5] != null) {  tutorial_cr = Integer.parseInt(obj[5].toString()); }
		if (obj[6] != null) {  internal_theory = Integer.parseInt(obj[6].toString()); }
		if (obj[7] != null) {  internal_practical = Integer.parseInt(obj[7].toString()); }
		if (obj[8] != null) {  final_theory = Integer.parseInt(obj[8].toString()); }
		if (obj[9] != null) {  semester_no = Integer.parseInt(obj[9].toString()); }
		if (obj[10] != null) {  status = Integer.parseInt(obj[10].toString()); }
		
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM subjects where 1=1");
			  
				if (subject_id != 0) {
		            query.append(" AND subject_id = ?");
		            parameters.add(subject_id);
		        }
				if (program_id != 0) {
		            query.append(" AND program_id = ?");
		            parameters.add(program_id);
		        }
				if (subject_name != null) {
		            query.append(" AND subject_name = ?");
		            parameters.add(subject_name);
		        }
				if (subject_code != null) {
		            query.append(" AND subject_code = ?");
		            parameters.add(subject_code);
		        }
				if (theory_cr != 0) {
		            query.append(" AND theory_cr = ?");
		            parameters.add(theory_cr);
		        }
				if (tutorial_cr != 0) {
		            query.append(" AND tutorial_cr = ?");
		            parameters.add(tutorial_cr);
		        }
				if (internal_theory != 0) {
		            query.append(" AND internal_theory = ?");
		            parameters.add(internal_theory);
		        }
				if (internal_practical != 0) {
		            query.append(" AND internal_practical = ?");
		            parameters.add(internal_practical);
		        }
				if (final_theory != 0) {
		            query.append(" AND final_theory = ?");
		            parameters.add(final_theory);
		        }
				if (semester_no != 0) {
		            query.append(" AND semester_no = ?");
		            parameters.add(semester_no);
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
			        		/*
			        		Map<String, Object> map= new HashMap<>();
			        		map.put("subject_id", rs.getInt("subject_id"));
			        		map.put("subject_name", rs.getString("subject_name"));
			        		map.put("subject_code", rs.getInt("subject_code"));
			        		map.put("theory_cr",rs.getString("theory_cr") );
			        		map.put("tutorial_cr", rs.getString("tutorial_cr"));
			        		map.put("internal_theory",rs.getString("internal_theory") );
			        		map.put("internal_practical", rs.getInt("internal_practical"));		
			        		map.put("final_theory", rs.getString("final_theory"));
			        		map.put("semester_no", rs.getString("semester_no"));
			        		map.put("status", rs.getString("status"));
			        		
			        		subjectModel.add(map);
			        		*/
			        		Subjects model = new Subjects();
							model.setSubject_id(rs.getInt("subject_id"));
							//model.setProgram_id(rs.getInt("program_id"));
							model.setFinal_theory(rs.getInt("final_theory"));
							model.setInternal_practical(rs.getInt("internal_practical"));
							model.setInternal_theory(rs.getInt("internal_theory"));
							model.setSemester_no(rs.getInt("semester_no"));
							model.setStatus(rs.getInt("status"));
							model.setSubject_code(rs.getString("subject_code"));
							model.setSubject_name(rs.getString("subject_name"));
							model.setSyllabus_file(rs.getString("syllabus_file"));
							model.setTheory_cr(rs.getInt("theory_cr"));
							model.setTutorial_cr(rs.getInt("tutorial_cr"));
							listSubjects.add(model);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Arrays.deepToString(listSubjects.toArray()));
		return listSubjects;

	}

	@Override
	public List getSubjectByProgram(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List listSubjects = new ArrayList<Subjects>();

		int subject_id = 0;
		int program_id = 0;
		String subject_name = null;
		String subject_code = null;
		int theory_cr = 0;
		int tutorial_cr = 0;
		int internal_theory = 0;
		int internal_practical = 0;
		int final_theory = 0;
		int semester_no = 0;
		int status = 0;


		if (obj[0] != null) {  subject_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  program_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  subject_name = obj[2].toString(); }
		if (obj[3] != null) {  subject_code = obj[3].toString(); }
		if (obj[4] != null) {  theory_cr = Integer.parseInt(obj[4].toString()); }
		if (obj[5] != null) {  tutorial_cr = Integer.parseInt(obj[5].toString()); }
		if (obj[6] != null) {  internal_theory = Integer.parseInt(obj[6].toString()); }
		if (obj[7] != null) {  internal_practical = Integer.parseInt(obj[7].toString()); }
		if (obj[8] != null) {  final_theory = Integer.parseInt(obj[8].toString()); }
		if (obj[9] != null) {  semester_no = Integer.parseInt(obj[9].toString()); }
		if (obj[10] != null) {  status = Integer.parseInt(obj[10].toString()); }
		
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM subjects as s INNER JOIN programs as p ON s.program_id = p.program_id where 1=1");
			  
				if (subject_id != 0) {
		            query.append(" AND subject_id = ?");
		            parameters.add(subject_id);
		        }
				if (program_id != 0) {
		            query.append(" AND s.program_id = ?");
		            parameters.add(program_id);
		        }
				if (subject_name != null) {
		            query.append(" AND subject_name = ?");
		            parameters.add(subject_name);
		        }
				if (subject_code != null) {
		            query.append(" AND subject_code = ?");
		            parameters.add(subject_code);
		        }
				if (theory_cr != 0) {
		            query.append(" AND theory_cr = ?");
		            parameters.add(theory_cr);
		        }
				if (tutorial_cr != 0) {
		            query.append(" AND tutorial_cr = ?");
		            parameters.add(tutorial_cr);
		        }
				if (internal_theory != 0) {
		            query.append(" AND internal_theory = ?");
		            parameters.add(internal_theory);
		        }
				if (internal_practical != 0) {
		            query.append(" AND internal_practical = ?");
		            parameters.add(internal_practical);
		        }
				if (final_theory != 0) {
		            query.append(" AND final_theory = ?");
		            parameters.add(final_theory);
		        }
				if (semester_no != 0) {
		            query.append(" AND semester_no = ?");
		            parameters.add(semester_no);
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
			        		/*
			        		Map<String, Object> map= new HashMap<>();
			        		map.put("subject_id", rs.getInt("subject_id"));
			        		map.put("subject_name", rs.getString("subject_name"));
			        		map.put("subject_code", rs.getInt("subject_code"));
			        		map.put("theory_cr",rs.getString("theory_cr") );
			        		map.put("tutorial_cr", rs.getString("tutorial_cr"));
			        		map.put("internal_theory",rs.getString("internal_theory") );
			        		map.put("internal_practical", rs.getInt("internal_practical"));		
			        		map.put("final_theory", rs.getString("final_theory"));
			        		map.put("semester_no", rs.getString("semester_no"));
			        		map.put("status", rs.getString("status"));
			        		
			        		subjectModel.add(map);
			        		*/
			        		
			        		Map<String, Object> map= new HashMap<>();
			        		
							map.put("subject_id",rs.getInt("subject_id"));
							map.put("program_id",rs.getInt("program_id"));
							map.put("final_theory",rs.getInt("final_theory"));
							map.put("internal_practical",rs.getInt("internal_practical"));
							map.put("internal_theory",rs.getInt("internal_theory"));
							map.put("semester_no",rs.getInt("semester_no"));
							map.put("status",rs.getInt("status"));
							map.put("subject_code",rs.getString("subject_code"));
							map.put("subject_name",rs.getString("subject_name"));
							map.put("syllabus_file",rs.getString("syllabus_file"));
							map.put("theory_cr",rs.getInt("theory_cr"));
							map.put("tutorial_cr",rs.getInt("tutorial_cr"));
							
							
							map.put("program_name",rs.getInt("program_name"));
							listSubjects.add(map);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Arrays.deepToString(listSubjects.toArray()));
		return listSubjects;
	}

}
