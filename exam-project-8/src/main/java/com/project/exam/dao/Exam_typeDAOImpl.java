package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.exam.model.Exam_type;
import com.project.exam.model.Program;

@Repository("exam_TypeDao")
public class Exam_typeDAOImpl implements Exam_typeDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public List<Exam_type> getExam_typeList() {
		List<Exam_type> listExam_type = new ArrayList<Exam_type>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from exam_types";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Exam_type model = new Exam_type();
				model.setExam_type_id(rs.getInt("exam_type_id"));
				model.setStatus(rs.getInt("status"));
				model.setType_name(rs.getString("type_name"));
				listExam_type.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listExam_type;
	}

	@Override
	public Exam_type addExam_type(Exam_type exam_type) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into exam_types(status,type_name) values(?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, exam_type.getStatus());
			pst.setString(col++, exam_type.getType_name());
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
			return exam_type;
		}
		return new Exam_type();
	}

	@Override
	public List<Exam_type> getExam_type(int s_Id) {
		List<Exam_type> listExam_type= new ArrayList<>();
		
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from exam_types where exam_type_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Exam_type model = new Exam_type();
				model.setExam_type_id(rs.getInt("exam_type_id"));
				model.setStatus(rs.getInt("status"));
				model.setType_name(rs.getString("type_name"));
				listExam_type.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listExam_type;
	}

	@Override
	public Exam_type updateExam_type(Exam_type exam_type) {
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update exam_types set status=?,type_name=? where exam_type_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, exam_type.getStatus());
			pst.setString(col++, exam_type.getType_name());
			pst.setInt(col++, exam_type.getExam_type_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return exam_type;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Exam_type();
	}

	@Override
	public int deleteExam_type(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from exam_types where exam_type_id =?";
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
	public List searchExamType(String searchPara) {
		List listOfReslut = new ArrayList<>();
		//System.out.println("search ="+searchPara);
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "SELECT * FROM exam_types WHERE type_name like '"+searchPara+"%'";
			pst=conn.prepareStatement(sql);
			rs = pst.executeQuery();
		//System.out.println("here");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Program s = new Program();
				map.put("id", rs.getInt("exam_type_id"));
				map.put("name", rs.getString("type_name"));			
				listOfReslut.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOfReslut;
	}


}
