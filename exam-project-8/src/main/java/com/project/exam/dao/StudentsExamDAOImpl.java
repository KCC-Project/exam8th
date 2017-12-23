package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.controller.StudentsExamController;
import com.project.exam.model.Exam;
import com.project.exam.model.Student;
import com.project.exam.model.StudentsExam;

@Repository("studentExamDao")
public class StudentsExamDAOImpl implements StudentsExamDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	@Transactional
	public List<StudentsExam> getstudentsExamList() {
		session = sessionFactory.getCurrentSession();
		List<StudentsExam> listStudentsExam = session.createCriteria(StudentsExam.class).list();
		return listStudentsExam;
	}

	@Override
	@Transactional
	public int addstudentsExam(StudentsExam studentsExam) {
		session = sessionFactory.getCurrentSession();
		int studentExamId=(Integer)session.save(studentsExam);
		System.out.println("studentExamId id after save is = "+studentExamId);
			return studentExamId;
	}

	@Override
	@Transactional
	public List<StudentsExam> getstudentsExam(int s_Id) {
		session = sessionFactory.getCurrentSession();
		String hql = "FROM StudentsExam where students_exams_id = '" + s_Id + "'";
		Query query = session.createQuery(hql);
		List<StudentsExam> studentExamList = query.getResultList();
		return studentExamList;
	}

	@Override
	@Transactional
	public StudentsExam updatestudentsExam(StudentsExam studentsExam) {
		session = sessionFactory.getCurrentSession();
		session.update(studentsExam);
		return studentsExam;
	}

	@Override
	@Transactional
	public int deletestudentsExam(int s_Id) {
		session = sessionFactory.getCurrentSession();
		StudentsExam student = session.get(StudentsExam.class, s_Id);
		session.delete(student);
		return 1;
	}

	@Override
	@Transactional
	public void getRequiredInfoTOSave(int programId, int semester, int examId) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT s_id FROM student s INNER JOIN studentprogram sp "
				+ "on sp.student_id=s.s_id "
				+ "WHERE sp.program_id="+programId+" AND sp.status=1 AND s.current_semester="+semester+" AND s.status=1");
		List result = query.getResultList();
		for (Object object : result) {
			//System.out.println("result is = "+object);
			StudentsExam se=new StudentsExam();
			Student s= new Student();
			Exam exam= new Exam();
			exam.setExam_id(examId);
			s.setS_id(Integer.parseInt(object.toString()));
			se.setStudent(s);
			se.setExam(exam);
			int studentExamId=(Integer)session.save(se);
			System.out.println("studentExamId auto = "+studentExamId);
			
		}
	}

	@Override
	public List updatestudentExamModel(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName) {
		System.out.println("inside sql");
		List list = new ArrayList<>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			System.out.println("inside sql");
			sql = "SELECT s.first_name,s.middle_name,s.last_name,se.attendance_status, se.obtained_marks,se.exam_id,se.students_exams_id,se.grade,se.status, sub.subject_name, et.type_name,e.full_marks,e.pass_marks, e.exam_date,se.s_id,s.current_semester, pr.program_name, sub.semester_no FROM students_exams as se INNER JOIN students as s ON se.s_id = s.s_id INNER JOIN students_program as sp ON s.s_id = sp.s_id INNER JOIN exams as e ON e.exam_id = se.exam_id INNER JOIN exam_types as et ON et.exam_type_id = e.exam_type_id INNER JOIN subjects as sub ON e.subject_id = sub.subject_id INNER JOIN programs as pr ON pr.program_id = sub.program_id where sp.program_id=? and sp.batch_year=? and s.current_semester=? and et.exam_type_id=? and sub.subject_id=?";
			System.out.println("inside sql");
			pst = conn.prepareStatement(sql);
			System.out.println("inside sql");
			pst.setInt(1, programId);
			System.out.println("inside sql");
			pst.setInt(2, batchyear);
			pst.setInt(3, semesterNo);
			System.out.println("inside sql");
			pst.setInt(4, examtypeId);
			pst.setInt(5, subjectId);
			System.out.println("inside sql");
			rs = pst.executeQuery();
			System.out.println("inside sql");
			while (rs.next()) {
				System.out.println(rs.getString("first_name"));
				Map<String, Object> map = new HashMap<>();
				map.put("first_name", rs.getString("first_name"));
				map.put("middle_name", rs.getString("middle_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("grade", rs.getInt("grade"));
				map.put("attendance_status", rs.getInt("attendance_status"));
				map.put("obtained_marks", rs.getInt("obtained_marks"));
				map.put("subject_name", rs.getString("subject_name"));
				map.put("type_name", rs.getString("type_name"));
				map.put("status", rs.getInt("status"));
				map.put("current_semester", rs.getInt("current_semester"));
				map.put("exam_date", rs.getString("exam_date"));
				map.put("s_id", rs.getInt("s_id"));
				map.put("full_marks", rs.getInt("full_marks"));
				map.put("pass_marks", rs.getInt("pass_marks"));
				map.put("students_exams_id", rs.getString("students_exams_id"));
				map.put("exam_id", rs.getInt("exam_id"));
				
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	@Override
	public StudentsExam getstudentsExam(int s_Id, int examid) {
		StudentsExam model = new StudentsExam();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_exams where students_exams_id=? and s_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, examid);
			pst.setInt(2, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.setStudents_exams_id(rs.getInt("students_exams_id"));
				model.setAttendance_status(rs.getInt("attendance_status"));
				model.setGrade(rs.getString("grade"));
				model.setObtained_marks(rs.getInt("obtained_marks"));
				model.setStatus(rs.getInt("status"));
				//model.setExam_id(rs.getInt("exam_id"));
				//model.setS_id(rs.getInt("s_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

}
