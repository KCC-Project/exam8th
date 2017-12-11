package com.project.exam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.exam.model.Program;
import com.project.exam.model.Student;

@Repository("programDao")
public class ProgramDAOImpl implements ProgramDAO {
	
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Program> getProgramList() {
		List<Program> listProgram = new ArrayList<Program>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from programs";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Program model = new Program();
				model.setProgram_id(rs.getInt("program_id"));
				model.setProgram_name(rs.getString("program_name"));
				model.setProgram_years(rs.getInt("program_years"));
				model.setStatus(rs.getInt("status"));
				model.setFaculty_id(rs.getInt("faculty_id"));
				listProgram.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listProgram;
	}

	@Override
	public Program addProgram(Program program) {
		boolean status = false;
	
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into programs(program_name,program_years,status,faculty_id) values(?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, program.getProgram_name());
			pst.setInt(col++, program.getProgram_years());
			pst.setInt(col++, program.getStatus());
			
			pst.setInt(col++, program.getFaculty_id());
			
			int count = pst.executeUpdate();
			if (count > 0) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error from saving program=" + e);
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
			return program;
		}
		return new Program();
	}

	@Override
	public List<Program> getProgram(int s_Id) {
		List<Program> listProgram= new ArrayList<>();
	
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from programs where program_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Program model= new Program();
				model.setProgram_id(rs.getInt("program_id"));
				model.setProgram_name(rs.getString("program_name"));
				model.setProgram_years(rs.getInt("program_years"));
				model.setStatus(rs.getInt("status"));
			
				model.setFaculty_id(rs.getInt("faculty_id"));
				listProgram.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listProgram;
	}

	@Override
	public Program updateProgram(Program program) {
		
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update programs set program_name=? , program_years=?, status=?,faculty_id=? where program_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, program.getProgram_name());
			pst.setInt(col++, program.getProgram_years());
			pst.setInt(col++, program.getStatus());
			
			pst.setInt(col++, program.getFaculty_id());
			pst.setInt(col++, program.getProgram_id());
			int count = pst.executeUpdate();
			if (count > 0) {
				
				return program;
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Program();
	}

	@Override
	public int deleteProgram(int s_Id) {
		int result = 0;
		//System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from programs where program_id =?";
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

	@Override
	public List<Program> getProgramListByFacultyId(int s_Id) {
		System.out.println("here indise== "+s_Id);
		List<Program> listProgram = new ArrayList<Program>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from programs where faculty_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Program model = new Program();
				System.out.println(rs.getString("program_name"));
				model.setProgram_id(rs.getInt("program_id"));
				model.setProgram_name(rs.getString("program_name"));
				model.setProgram_years(rs.getInt("program_years"));
				model.setStatus(rs.getInt("status"));
			
				model.setFaculty_id(rs.getInt("faculty_id"));
				listProgram.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listProgram;
	}

	@Override
	public List searchProgram(String searchPara) {
		List listOfReslut = new ArrayList<>();
		//System.out.println("search ="+searchPara);
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "SELECT * FROM programs WHERE program_name like '"+searchPara+"%'";
			pst=conn.prepareStatement(sql);
			rs = pst.executeQuery();
		//System.out.println("here");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Program s = new Program();
				map.put("id", rs.getInt("program_id"));
				map.put("name", rs.getString("program_name"));			
				listOfReslut.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOfReslut;
	}
	

}
