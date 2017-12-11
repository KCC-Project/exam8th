package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.exam.model.StudentsProgram;

@Repository("/studemtsProgramDao")
public class StudentsProgramDAOImpl implements StudentsProgramDAO {

	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<StudentsProgram> getStudentsProgramList() {
		List<StudentsProgram> listStudentsProgram = new ArrayList<StudentsProgram>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_program";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentsProgram model = new StudentsProgram();
				model.setStudent_program_id(rs.getInt("student_program_id"));
				model.setBatch_year(rs.getInt("batch_year"));
				model.setEnroll_date(rs.getString("enroll_date"));
				model.setStatus(rs.getInt("status"));
				model.setProgram_id(rs.getInt("program_id"));
				model.setS_id(rs.getInt("s_id"));
				listStudentsProgram.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStudentsProgram;
	}

	@Override
	public StudentsProgram addStudentsProgram(StudentsProgram studentsProgram) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into students_program(batch_year,enroll_date,status,program_id,s_id) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;

			pst.setInt(col++, studentsProgram.getBatch_year());
			pst.setString(col++, studentsProgram.getEnroll_date());
			pst.setInt(col++, studentsProgram.getStatus());
			pst.setInt(col++, studentsProgram.getProgram_id());
			pst.setInt(col++, studentsProgram.getS_id());

			int count = pst.executeUpdate();
			if (count > 0) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error from saving studentsProgram=" + e);
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
			return studentsProgram;
		}
		return new StudentsProgram();
	}

	@Override
	public StudentsProgram getStudentsProgram(int s_Id) {
		StudentsProgram model = new StudentsProgram();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_program where student_program_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.setStudent_program_id(rs.getInt("student_program_id"));
				model.setBatch_year(rs.getInt("batch_year"));
				model.setEnroll_date(rs.getString("enroll_date"));
				model.setStatus(rs.getInt("status"));
				model.setProgram_id(rs.getInt("program_id"));
				model.setS_id(rs.getInt("s_id"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}
	@Override
	public StudentsProgram getStudentsProgramByStudentId(int s_Id) {
		StudentsProgram model = new StudentsProgram();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_program where s_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.setStudent_program_id(rs.getInt("student_program_id"));
				model.setBatch_year(rs.getInt("batch_year"));
				model.setEnroll_date(rs.getString("enroll_date"));
				model.setStatus(rs.getInt("status"));
				model.setProgram_id(rs.getInt("program_id"));
				model.setS_id(rs.getInt("s_id"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}
	@Override
	public StudentsProgram updateStudentsProgram(StudentsProgram studentsProgram) {

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update students_program set batch_year=? , enroll_date=?, status=?, program_id=?,s_id=? where student_program_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, studentsProgram.getBatch_year());
			pst.setString(col++, studentsProgram.getEnroll_date());
			pst.setInt(col++, studentsProgram.getStatus());
			pst.setInt(col++, studentsProgram.getProgram_id());
			pst.setInt(col++, studentsProgram.getS_id());
			pst.setInt(col++, studentsProgram.getStudent_program_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return studentsProgram;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new StudentsProgram();
	}

	@Override
	public int deleteStudentsProgram(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from students_program where student_program_id =?";
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
	public List<StudentsProgram> getStudentsProgramByProgramId(int s_Id) {
		List<StudentsProgram> listStudentsProgram = new ArrayList<StudentsProgram>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students_program where program_id=? ORDER BY batch_year";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentsProgram model = new StudentsProgram();
				model.setStudent_program_id(rs.getInt("student_program_id"));
				model.setBatch_year(rs.getInt("batch_year"));
				model.setEnroll_date(rs.getString("enroll_date"));
				model.setStatus(rs.getInt("status"));
				model.setProgram_id(rs.getInt("program_id"));
				model.setS_id(rs.getInt("s_id"));
				listStudentsProgram.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStudentsProgram;
	}

	@Override
	public List<StudentsProgram> searchByField(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List<StudentsProgram> studentsProgramModel = new ArrayList<>();

		int student_program_id = 0;
		int s_id = 0;
		int program_id = 0;
		int batch_year = 0;
		String enroll_date = null;
		int status = 0;

		if (obj[0] != null) {  student_program_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  s_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  program_id = Integer.parseInt(obj[2].toString()); }
		if (obj[3] != null) {  batch_year = Integer.parseInt(obj[3].toString()); }
		if (obj[4] != null) {  enroll_date = obj[4].toString(); }
		if (obj[5] != null) {  status = Integer.parseInt(obj[5].toString()); }
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM students_program WHERE 1=1");
			  
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
			        		StudentsProgram model = new StudentsProgram();
			        		
			        		model.setStudent_program_id(rs.getInt("student_program_id"));
							model.setBatch_year(rs.getInt("batch_year"));
							model.setEnroll_date(rs.getString("enroll_date"));
							model.setStatus(rs.getInt("status"));
							model.setProgram_id(rs.getInt("program_id"));
							model.setS_id(rs.getInt("s_id"));
							studentsProgramModel.add(model);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return studentsProgramModel;
	}

	@Override
	public void saveStudentProgram(int programID, int batch, String enrollDate) {
	try {
		conn = DatabaseConnection.connectToDatabase();
		sql = "SELECT * FROM students ORDER BY s_id DESC LIMIT 1";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()) {
			sql = "insert into students_program(batch_year,enroll_date,status,program_id,s_id) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;

			pst.setInt(col++, batch);
			pst.setString(col++, enrollDate);
			pst.setInt(col++, 0);
			pst.setInt(col++, programID);
			pst.setInt(col++, rs.getInt("s_id"));
			int count = pst.executeUpdate();
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	}

}
