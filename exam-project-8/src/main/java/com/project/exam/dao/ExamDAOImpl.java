package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.exam.model.Admin;
import com.project.exam.model.Exam;
import com.project.exam.model.StudentsProgram;

@Repository("examDao")
public class ExamDAOImpl implements ExamDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Exam> getExamList() {
		List<Exam> listAdmin = new ArrayList<Exam>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from exams";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Exam model = new Exam();
				model.setExam_id(rs.getInt("exam_id"));
				model.setExam_date(rs.getString("exam_date"));
				model.setFull_marks(rs.getInt("full_marks"));
				model.setPass_marks(rs.getInt("pass_marks"));
				model.setStatus(rs.getInt("status"));
				model.setTime_from(rs.getString("time_from"));
				model.setTime_to(rs.getString("time_to"));
				model.setExam_type_id(rs.getInt("exam_type_id"));
				model.setSubject_id(rs.getInt("subject_id"));
				listAdmin.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listAdmin;
	}

	@Override
	public Exam addExam(Exam exam) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into exams(exam_date,full_marks,pass_marks,status,time_from,time_to,exam_type_id,subject_id) values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, exam.getExam_date());
			pst.setInt(col++, exam.getFull_marks());
			pst.setInt(col++, exam.getPass_marks());
			pst.setInt(col++, exam.getStatus());
			pst.setString(col++, exam.getTime_from());
			pst.setString(col++, exam.getTime_to());
			pst.setInt(col++, exam.getExam_type_id());
			pst.setInt(col++, exam.getSubject_id());
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
			return exam;
		}
		return new Exam();
	}

	@Override
	public Exam getExam(int s_Id) {
		Exam model = new Exam();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from exams where exam_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.setExam_id(rs.getInt("exam_id"));
				model.setExam_date(rs.getString("exam_date"));
				model.setFull_marks(rs.getInt("full_marks"));
				model.setPass_marks(rs.getInt("pass_marks"));
				model.setStatus(rs.getInt("status"));
				model.setTime_from(rs.getString("time_from"));
				model.setTime_to(rs.getString("time_to"));
				model.setExam_type_id(rs.getInt("exam_type_id"));
				model.setSubject_id(rs.getInt("subject_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

	@Override
	public Exam updateExam(Exam exam) {
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update exams set exam_date=?,full_marks=?,pass_marks=?,status=?,time_from=?,time_to=?,exam_type_id=?,subject_id=? where exam_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, exam.getExam_date());
			pst.setInt(col++, exam.getFull_marks());
			pst.setInt(col++, exam.getPass_marks());
			pst.setInt(col++, exam.getStatus());
			pst.setString(col++, exam.getTime_from());
			pst.setString(col++, exam.getTime_to());
			pst.setInt(col++, exam.getExam_type_id());
			pst.setInt(col++, exam.getSubject_id());
			pst.setInt(col++, exam.getExam_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return exam;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Exam();
	}

	@Override
	public int deleteExam(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from exams where exam_id =?";
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
	public List<Exam> searchByField(Object[] obj) {
		
		List<Object> parameters = new ArrayList<Object>();
		List<Exam> examModel = new ArrayList<>();

		int exam_id = 0;
		int exam_type_id = 0;
		int subject_id = 0;
		String exam_date = null;
		int full_marks = 0;
		int pass_marks = 0;
		int status = 0;
		String time_from = null;
		String time_to = null;

		if (obj[0] != null) {
			exam_id = Integer.parseInt(obj[0].toString());
		}
		if (obj[1] != null) {
			exam_type_id = Integer.parseInt(obj[1].toString());
		}
		if (obj[2] != null) {
			subject_id = Integer.parseInt(obj[2].toString());
		}
		if (obj[3] != null) {
			exam_date = obj[3].toString();
		}
		if (obj[4] != null) {
			full_marks = Integer.parseInt(obj[4].toString());
		}
		if (obj[5] != null) {
			pass_marks = Integer.parseInt(obj[5].toString());
		}
		if (obj[6] != null) {
			status = Integer.parseInt(obj[6].toString());
		}
		if (obj[7] != null) {
			time_from = obj[7].toString();
		}
		if (obj[8] != null) {
			time_to = obj[8].toString();
		}
		
		try {
			StringBuilder query = new StringBuilder("SELECT * FROM exams WHERE 1=1 ");

			if (exam_id != 0) {
				query.append(" AND exam_id = ? ");
				parameters.add(exam_id);
			}
			if (exam_type_id != 0) {
				query.append(" AND exam_type_id = ?");
				parameters.add(exam_type_id);
			}
			if (subject_id != 0) {
				query.append(" AND subject_id = ?");
				parameters.add(subject_id);
			}
			if (exam_date != null) {
				query.append(" AND exam_date = ?");
				parameters.add(exam_date);
			}
			if (full_marks != 0) {
				query.append(" AND full_marks = ?");
				parameters.add(full_marks);
			}
			if (pass_marks != 0) {
				query.append(" AND pass_marks = ?");
				parameters.add(pass_marks);
			}
			if (status != 0) {
				query.append(" AND status = ?");
				parameters.add(status);
			}
			if (time_from != null) {
				query.append(" AND time_from = ?");
				parameters.add(time_from);
			}
			if (time_to != null) {
				query.append(" AND time_to = ?");
				parameters.add(time_to);

				}
query.append(" ORDER BY exam_id asc");
			String Query = query.toString();
			System.out.println("query of exam = "+Query);

			conn = DatabaseConnection.connectToDatabase();
			pst = conn.prepareStatement(Query);

			int i = 1;
			for (Object parameter : parameters) {
				pst.setObject(i++, parameter);}
				rs = pst.executeQuery();
				if (rs != null) {

					while (rs.next()) {
						Exam model = new Exam();

						model.setExam_id(rs.getInt("exam_id"));
						model.setExam_date(rs.getString("exam_date"));
						model.setFull_marks(rs.getInt("full_marks"));
						model.setPass_marks(rs.getInt("pass_marks"));
						model.setStatus(rs.getInt("status"));
						model.setTime_from(rs.getString("time_from"));
						model.setTime_to(rs.getString("time_to"));
						model.setExam_type_id(rs.getInt("exam_type_id"));
						model.setSubject_id(rs.getInt("subject_id"));
						examModel.add(model);
						//System.out.println("exam model data = "+model.toString());
					}

				}
			
		} catch (Exception e) {
			// TODO: handle exception

		
	
	}
		return examModel;

	}
}