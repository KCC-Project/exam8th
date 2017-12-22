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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.entity.Students;
import com.project.exam.model.Exam_type;
import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<Student> getStudentList() {
		session = sessionFactory.getCurrentSession();
		List<Student> listStudent = session.createCriteria(Student.class).list();
		return listStudent;
	}

	@Override
	@Transactional
	public int addStudent(Student student) {
		session = sessionFactory.getCurrentSession();
	int studentId=(Integer)session.save(student);
	System.out.println("student id after save is = "+studentId);
		return studentId;
	}

	@Override
	@Transactional
	public List<Student> getStudent(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Student where s_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<Student> studentList = query.getResultList();
		return studentList;
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		session = sessionFactory.getCurrentSession();
		session.update(student);
		return student;
	}

	@Override
	@Transactional
	public int deleteStudent(int s_Id) {
		session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, s_Id);
		session.delete(student);
		return 1;
	}

	@Override
	@Transactional
	public List searchStudent(String searchPara) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Student where first_name like '" + searchPara + "%'";
		Query query = session.createQuery(hql);
		List<Student> studentList = query.getResultList();
		List listOfReslut = new ArrayList<>();
		for (Student studentList1 : studentList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", studentList1.getS_id());
			String name = null;
			try {
				if (studentList1.getMiddle_name()== null) {
					name = studentList1.getFirst_name() + " " +  studentList1.getLast_name();
				} else {
					name = studentList1.getFirst_name() + " " + studentList1.getMiddle_name() + " " +studentList1.getLast_name();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			map.put("name", name);
			map.put("image",studentList1.getImage());
			listOfReslut.add(map);
		}

		return listOfReslut;
	}

	@Override
	@Transactional
	public List<Student> search(int id,int year) {
		session = sessionFactory.getCurrentSession();
		List<StudentsProgram> listStudent = session.createCriteria(StudentsProgram.class).add(Restrictions.eq("program.program_id", id)).add(Restrictions.eq("batch_year",year)).list();
		List studentList = new ArrayList<>();
		for (StudentsProgram studentsProgram : listStudent) {
			Student s=(Student)session.get(Student.class, studentsProgram.getStudent().getS_id());
			studentList.add(s);
		}	
		return studentList;
	}
	
	@Override
	public List getStudentsByStudentsProgram(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List studentsModel = new ArrayList<>();

		int student_program_id = 0;
		int s_id = 0;
		int program_id = 0;
		int batch_year = 0;
		String enroll_date = null;
		int status = 0;
		String username = null;

		if (obj[0] != null) {  student_program_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  s_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  program_id = Integer.parseInt(obj[2].toString()); }
		if (obj[3] != null) {  batch_year = Integer.parseInt(obj[3].toString()); }
		if (obj[4] != null) {  enroll_date = obj[4].toString(); }
		if (obj[5] != null) {  status = Integer.parseInt(obj[5].toString()); }
		
		System.out.println("from bd ="+program_id+  " "+batch_year);
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM students as s  INNER JOIN students_program as sp ON s.s_id = sp.s_id  where 1=1");
			  
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
			        		Map<String, Object> map= new HashMap<>();
			        		map.put("s_id", rs.getInt("s_id"));
			        		map.put("address", rs.getString("address"));
			        		map.put("current_semester", rs.getInt("current_semester"));
			        		map.put("date_of_birth",rs.getString("date_of_birth") );
			        		map.put("email", rs.getString("email"));
			        		map.put("first_name",rs.getString("first_name") );
			        		map.put("gender", rs.getInt("gender"));
			        		map.put("image", rs.getString("image"));
			        		map.put("last_name", rs.getString("last_name"));
			        		map.put("middle_name", rs.getString("middle_name"));
			        		map.put("password", rs.getString("password"));
			        		map.put("phone",rs.getString("phone") );
			        		map.put("status", rs.getInt("status"));
			        		map.put("username",rs.getString("username") );
			        	
			        		map.put("student_program_id", rs.getInt("student_program_id"));
			        		map.put("batch_year",rs.getInt("batch_year") );
			        		map.put("enroll_date", rs.getString("enroll_date"));
			        		map.put("status",rs.getInt("status") );
			        		map.put("program_id",rs.getInt("program_id") );
		      		
			        		studentsModel.add(map);
			        		
						
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Arrays.deepToString(studentsModel.toArray()));
		return studentsModel;
	}

	

}
