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
import com.project.exam.model.Student;
import com.project.exam.model.StudentsExam;

@Repository("studentExamDao")
public class StudentsExamDAOImpl implements StudentsExamDAO {

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
		int studentExamId = (Integer) session.save(studentsExam);
		System.out.println("studentExamId id after save is = " + studentExamId);
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
				+ "on sp.student_id=s.s_id " + "WHERE sp.program_id=" + programId
				+ " AND sp.status=1 AND s.current_semester=" + semester + " AND s.status=1");
		List result = query.getResultList();
		for (Object object : result) {
			// System.out.println("result is = "+object);
			StudentsExam se = new StudentsExam();
			Student s = new Student();
			Exam exam = new Exam();
			exam.setExam_id(examId);
			s.setS_id(Integer.parseInt(object.toString()));
			se.setStudent(s);
			se.setExam(exam);
			int studentExamId = (Integer) session.save(se);
			System.out.println("studentExamId auto = " + studentExamId);

		}
	}

	@Override
	@Transactional
	public List<StudentsExam> searchByField(int studentId) {
		session = sessionFactory.getCurrentSession();
		List<StudentsExam> listexam = session.createCriteria(StudentsExam.class)
				.add(Restrictions.eq("student.s_id", studentId)).list();

		return listexam;

	}

	@Override
	@Transactional
	public List updatestudentExamModel(int semesterNo, String programeName, int programId, int batchyear,
			String examTypeName, int subjectId, int examtypeId, String subjectName) {
		List list = new ArrayList<>();

		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT e.exam_id ,sub.subject_name, et.type_name,e.exam_date,e.full_marks,e.pass_marks FROM exam e INNER JOIN subjects sub ON sub.subject_id=e.subject_id "
						+ "INNER JOIN exam_type et ON et.exam_type_id=e.exam_type_id WHERE et.exam_type_id="
						+ examtypeId + " AND sub.subject_id=" + subjectId + " " + "AND e.status=0;");
		List<Object[]> result = query.getResultList();
		for (Object[] objects : result) {
			/*
			 * System.out.println("exam_id = "+objects[0]);
			 * System.out.println("subject name = "+objects[1]);
			 * System.out.println("exam type name = "+objects[2]);
			 */
			Query query1 = session
					.createSQLQuery("SELECT CONCAT(s.first_name ,' ',s.middle_name,' ' ,s.last_name) AS fullname ,"
							+ " s.s_id,s.current_semester,se.students_exams_id,se.attendance_status,se.grade,"
							+ "se.obtained_marks,se.status FROM student s INNER JOIN student_exam se "
							+ "ON se.student_id=s.s_id INNER JOIN exam e ON e.exam_id=se.exam_id " + "WHERE e.exam_id="
							+ objects[0] + " AND s.status=1");
			List<Object[]> result1 = query1.getResultList();
			for (Object[] objects2 : result1) {
				/*
				 * System.out.println("fullname = "+objects2[0]);
				 * System.out.println("student id = "+objects2[1]);
				 * System.out.println("current semester = "+objects2[2]);
				 * System.out.println("student exam id = "+objects2[3]);
				 * System.out.println("attandance = "+objects2[4]);
				 * System.out.println("grade = "+objects2[5]);
				 * System.out.println("obtain marks = "+objects2[6]);
				 * System.out.println("status = "+objects2[7]);
				 */

				Map<String, Object> map = new HashMap<>();
				map.put("exam_id", objects[0]);
				map.put("subject_name", objects[1]);
				map.put("type_name", objects[2]);
				map.put("exam_date", objects[3]);
				map.put("full_marks", objects[4]);
				map.put("pass_marks", objects[5]);
				map.put("fullname", objects2[0]);
				map.put("s_id", objects2[1]);
				map.put("current_semester", objects2[2]);
				map.put("students_exams_id", objects2[3]);
				map.put("attendance_status", objects2[4]);
				map.put("grade", objects2[5]);
				map.put("obtained_marks", objects2[6]);
				map.put("status", objects2[7]);
				list.add(map);

			}
		}
		return list;
	}

	@Override
	@Transactional
	public List<StudentsExam> getstudentsExam(int s_Id, int semesterNo) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT stex.students_exams_id,stex.obtained_marks,stex.grade,stex.status,"
				+ "stex.attendance_status,sub.subject_name,sub.semester_no,ex.exam_id,ex.exam_date,ex.full_marks,"
				+ "ex.pass_marks,exty.type_name,stex.student_id FROM student_exam stex INNER JOIN exam ex ON ex.exam_id=stex.exam_id "
				+ "INNER JOIN subjects sub ON sub.subject_id=ex.subject_id INNER JOIN exam_type exty ON exty.exam_type_id=ex.exam_type_id WHERE stex.student_id="+s_Id+" AND sub.semester_no="+semesterNo+" order by type_name");
		List<Object[]>  result = query.getResultList();
		System.out.println("Result size = "+result.size());
		List list = new ArrayList<>();
		for (Object[] object : result) {
			Map<String, Object> map= new HashMap<>();
			map.put("students_exams_id", object[0]);
			map.put("obtained_marks", object[1]);
			map.put("grade", object[2]);
			map.put("status", object[3]);
			map.put("attendance_status", object[4]);
			map.put("subject_name", object[5]);
			map.put("semester_no", object[6]);
			map.put("exam_id", object[7]);
			map.put("exam_date", object[8]);
			map.put("full_marks", object[9]);
			map.put("pass_marks", object[10]);
			map.put("type_name", object[11]);
			map.put("s_id", object[12]);
			list.add(map);
		}
		return list;
	}

	@Override
	@Transactional
	public List getstudentsExam(int semesterNo, String programeName, int programId, int batchyear, String examTypeName,
			int subjectId, int examtypeId, String subjectName) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT stex.students_exams_id,stex.obtained_marks,stex.grade,stex.status,stex.attendance_status"
				+ ",sub.subject_name,sub.semester_no,ex.exam_id,ex.exam_date,ex.full_marks,ex.pass_marks,exty.type_name, stu.first_name"
				+ ",stu.middle_name,stu.last_name FROM exam ex INNER JOIN exam_type exty ON exty.exam_type_id=ex.exam_type_id "
				+ "INNER JOIN subjects sub ON sub.subject_id=ex.subject_id INNER JOIN student_exam stex ON stex.exam_id=ex.exam_id "
				+ "INNER JOIN student stu ON stu.s_id= stex.student_id INNER JOIN studentprogram stupro ON stupro.student_id=stu.s_id "
				+ "INNER JOIN program p ON p.program_id=stupro.program_id WHERE exty.exam_type_id="+examtypeId+" AND sub.semester_no="+semesterNo+" AND "
				+ "stupro.batch_year="+batchyear+" AND sub.subject_id="+subjectId+" AND p.program_id="+programId+"");
		List<Object[]>  result = query.getResultList();
		System.out.println("Result size = "+result.size());
		List list = new ArrayList<>();
		for (Object[] object : result) {
			Map<String, Object> map= new HashMap<>();
			map.put("students_exams_id", object[0]);
			map.put("obtained_marks", object[1]);
			map.put("grade", object[2]);
			map.put("status", object[3]);
			map.put("attendance_status", object[4]);
			map.put("subject_name", object[5]);
			map.put("semester_no", object[6]);
			map.put("exam_id", object[7]);
			map.put("exam_date", object[8]);
			map.put("full_marks", object[9]);
			map.put("pass_marks", object[10]);
			map.put("type_name", object[11]);
			map.put("name", object[12]+ " "+object[13]+ " "+object[14]);
			list.add(map);
		}
		return list;
	}

}
