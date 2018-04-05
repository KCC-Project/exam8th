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

import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO {

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
	@Transactional
	public List<Student> getStudent(String email) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM Student where email = '" + email + "'";
		Query query = session.createQuery(hql);
		List<Student> studentList = query.getResultList();
		return studentList;
	}

	@Override
	@Transactional
	public String UpdateStudentsSemester(int program_id, int batch_year, int increment_value) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("UPDATE student SET current_semester="+increment_value+" WHERE EXISTS "
				+ "(SELECT * FROM studentprogram as sp where program_id="+program_id+" and batch_year="+batch_year+" and  sp.student_id=student.s_id)");
		int result = query.executeUpdate();
		if(result>0) {
			return "Semester of "+result+" Students Updated";
		}
		return "No rows were effected";
	}
	
}
