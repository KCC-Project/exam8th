package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.exam.model.Faculty;

@Repository("FacultyDao")
public class FacultyDAOImpl implements FacultyDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Faculty> getFacultyList() {
		List<Faculty> listFaculty = new ArrayList<Faculty>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from faculty";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Faculty model = new Faculty();
				model.setFaculty_id(rs.getInt("faculty_id"));
				model.setFaculty_name(rs.getString("faculty_name"));
				model.setStatus(rs.getInt("status"));
				listFaculty.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listFaculty;
	}

	@Override
	public Faculty addFaculty(Faculty faculty) {
		boolean status = false;
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into faculty(faculty_name,status) values(?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, faculty.getFaculty_name());
		
			pst.setInt(col++, faculty.getStatus());
			int count = pst.executeUpdate();
			if (count > 0) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error from saving faculty=" + e);
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (status==true) {
			return faculty;
		}
		return new Faculty();
	}

	@Override
	public Faculty getFaculty(int s_Id) {
		Faculty model= new Faculty();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from faculty where faculty_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				model.setFaculty_id(rs.getInt("faculty_id"));
				model.setFaculty_name(rs.getString("faculty_name"));
				model.setStatus(rs.getInt("status"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {
		
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update faculty set faculty_name=? , status=? where faculty_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			
			pst.setString(col++, faculty.getFaculty_name());
			pst.setInt(col++, faculty.getStatus());
			pst.setInt(col++, faculty.getFaculty_id());
			int count = pst.executeUpdate();
			if (count > 0) {
				
				return faculty;
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Faculty();
	}

	@Override
	public int deleteFaculty(int s_Id) {
		int result = 0;
		//System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from faculty where faculty_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1,s_Id);
			result = pst.executeUpdate();
		} catch (Exception e) {
			//System.out.println("Error in deleting examInfo model="+e.getMessage());
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

}
