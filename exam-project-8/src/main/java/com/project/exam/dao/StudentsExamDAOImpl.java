package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.exam.controller.StudentsExamController;
import com.project.exam.model.StudentsExam;

@Repository("studentExamDao")
public class StudentsExamDAOImpl implements StudentsExamDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<StudentsExam> getstudentsExamList() {
		List<StudentsExam> listStudentsExam = new ArrayList<StudentsExam>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_exams";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentsExam model = new StudentsExam();
				model.setStudents_exams_id(rs.getInt("students_exams_id"));
				model.setAttendance_status(rs.getInt("attendance_status"));
				model.setGrade(rs.getString("grade"));
				model.setObtained_marks(rs.getInt("obtained_marks"));
				model.setStatus(rs.getInt("status"));
				model.setExam_id(rs.getInt("exam_id"));
				model.setS_id(rs.getInt("s_id"));
				listStudentsExam.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStudentsExam;
	}

	@Override
	public StudentsExam addstudentsExam(StudentsExam studentsExam) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into students_exams(attendance_status,grade,obtained_marks,status,exam_id,s_id) values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, studentsExam.getAttendance_status());
			pst.setString(col++, studentsExam.getGrade());
			pst.setInt(col++, studentsExam.getObtained_marks());
			pst.setInt(col++, studentsExam.getStatus());
			pst.setInt(col++, studentsExam.getExam_id());
			pst.setInt(col++, studentsExam.getS_id());
			int count = pst.executeUpdate();
			if (count > 0) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error from saving admin=" + e);
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (status == true) {
			return studentsExam;
		}
		return new StudentsExam();
	}

	@Override
	public StudentsExam getstudentsExam(int s_Id) {
		StudentsExam model = new StudentsExam();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_exams where students_exams_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.setStudents_exams_id(rs.getInt("students_exams_id"));
				model.setAttendance_status(rs.getInt("attendance_status"));
				model.setGrade(rs.getString("grade"));
				model.setObtained_marks(rs.getInt("obtained_marks"));
				model.setStatus(rs.getInt("status"));
				model.setExam_id(rs.getInt("exam_id"));
				model.setS_id(rs.getInt("s_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

	@Override
	public StudentsExam updatestudentsExam(StudentsExam studentsExam) {
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update students_exams set attendance_status=?,grade=?,obtained_marks=?,status=?,exam_id=?,s_id=? where students_exams_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, studentsExam.getAttendance_status());
			pst.setString(col++, studentsExam.getGrade());
			pst.setInt(col++, studentsExam.getObtained_marks());
			pst.setInt(col++, studentsExam.getStatus());
			pst.setInt(col++, studentsExam.getExam_id());
			pst.setInt(col++, studentsExam.getS_id());
			pst.setInt(col++, studentsExam.getStudents_exams_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return studentsExam;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new StudentsExam();
	}

	@Override
	public int deletestudentsExam(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from students_exams where students_exams_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, s_Id);
			result = pst.executeUpdate();
		} catch (Exception e) {
			// System.out.println("Error in deleting examInfo model="+e.getMessage());
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	@Override
	public void getRequiredInfoTOSave(int a_program_id, int examTypeId, int semester_no) {
		List<StudentsExam> listOfInfo = new ArrayList();
		System.out.println("inside db");
		try {
			conn = DatabaseConnection.connectToDatabase();
			// sql="SELECT DISTINCT s.subject_id FROM subjects as s INNER JOIN exams as e ON
			// s.subject_id = e.subject_id INNER JOIN exam_types as et ON e.exam_id =
			// et.exam_type_id where s.semester_no=? and s.program_id=?";
			sql = "SELECT * FROM exams ORDER BY exam_id DESC LIMIT 1";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println("inside db");
			while (rs.next()) {

				int examId = rs.getInt("exam_id");
				sql = "SELECT s.s_id FROM students as s INNER JOIN students_program as sp ON s.s_id = sp.s_id INNER JOIN programs as p ON sp.program_id = p.program_id where sp.program_id=? and s.current_semester=?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, a_program_id);
				pst.setInt(2, semester_no);
				rs = pst.executeQuery();
				while (rs.next()) {
					sql = "insert into students_exams(attendance_status,grade,obtained_marks,status,exam_id,s_id) values(?,?,?,?,?,?)";
					pst = conn.prepareStatement(sql);
					int col = 1;
					pst.setInt(col++, 0);
					pst.setInt(col++, 0);
					pst.setInt(col++, 0);
					pst.setInt(col++, 0);
					pst.setInt(col++, examId);
					pst.setInt(col++, rs.getInt("s_id"));

					int count = pst.executeUpdate();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
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
				model.setExam_id(rs.getInt("exam_id"));
				model.setS_id(rs.getInt("s_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

}
