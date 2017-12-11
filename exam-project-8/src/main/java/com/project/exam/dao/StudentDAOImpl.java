package com.project.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.exam.model.Student;
import com.project.exam.model.StudentsProgram;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Student> getStudentList() {
		List<Student> listStudent = new ArrayList<Student>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Student model = new Student();
				model.setS_id(rs.getInt("s_id"));
				model.setAddress(rs.getString("address"));
				model.setCurrent_semester(rs.getInt("current_semester"));
				model.setDate_of_birth(rs.getString("date_of_birth"));
				model.setEmail(rs.getString("email"));
				model.setFirst_name(rs.getString("first_name"));
				model.setGender(rs.getInt("gender"));
				model.setImage(rs.getString("image"));
				model.setLast_name(rs.getString("last_name"));
				model.setMiddle_name(rs.getString("middle_name"));
				model.setPassword(rs.getString("password"));
				model.setPhone(rs.getString("phone"));
				model.setStatus(rs.getInt("status"));
				model.setUsername(rs.getString("username"));
				
				
				listStudent.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStudent;
	}

	@Override
	public Student addStudent(Student student) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into students(address,current_semester,date_of_birth,email,first_name,gender,image,last_name,middle_name,password,phone,status,username) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, student.getAddress());
			pst.setInt(col++, student.getCurrent_semester());
			pst.setString(col++, student.getDate_of_birth());
			pst.setString(col++, student.getEmail());
			pst.setString(col++, student.getFirst_name());
			pst.setInt(col++, student.getGender());
			pst.setString(col++, student.getImage());
			pst.setString(col++, student.getLast_name());
			pst.setString(col++, student.getMiddle_name());
			pst.setString(col++, student.getPassword());
			pst.setString(col++, student.getPhone());
			pst.setInt(col++, student.getStatus());
			pst.setString(col++, student.getUsername());
			
			
			

			int count = pst.executeUpdate();
			if (count > 0) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error from saving students=" + e);
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
			return student;
		}
		return new Student();
	}

	@Override
	public List<Student> getStudent(int s_Id) {
		List<Student> listStudent= new ArrayList<>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from students where s_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Student model = new Student();
				model.setS_id(rs.getInt("s_id"));
				model.setAddress(rs.getString("address"));
				model.setCurrent_semester(rs.getInt("current_semester"));
				model.setDate_of_birth(rs.getString("date_of_birth"));
				model.setEmail(rs.getString("email"));
				model.setFirst_name(rs.getString("first_name"));
				model.setGender(rs.getInt("gender"));
				model.setImage(rs.getString("image"));
				model.setLast_name(rs.getString("last_name"));
				model.setMiddle_name(rs.getString("middle_name"));
				model.setPassword(rs.getString("password"));
				model.setPhone(rs.getString("phone"));
				model.setStatus(rs.getInt("status"));
				model.setUsername(rs.getString("username"));
				listStudent.add(model);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStudent;
	}

	@Override
	public Student updateStudent(Student student) {
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update students set address=?,current_semester=?,date_of_birth=?,email=?,first_name=?,gender=?,image=?,last_name=?,middle_name=?,password=?,phone=?,status=?,username=? where s_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setString(col++, student.getAddress());
			pst.setInt(col++, student.getCurrent_semester());
			pst.setString(col++, student.getDate_of_birth());
			pst.setString(col++, student.getEmail());
			pst.setString(col++, student.getFirst_name());
			pst.setInt(col++, student.getGender());
			pst.setString(col++, student.getImage());
			pst.setString(col++, student.getLast_name());
			pst.setString(col++, student.getMiddle_name());
			pst.setString(col++, student.getPassword());
			pst.setString(col++, student.getPhone());
			pst.setInt(col++, student.getStatus());
			pst.setString(col++, student.getUsername());
			pst.setInt(col++, student.getS_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return student;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Student();
	}

	@Override
	public int deleteStudent(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from students where s_id =?";
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
	public List searchStudent(String searchPara) {
		
		List listOfReslut = new ArrayList<>();
		//System.out.println("search ="+searchPara);
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "SELECT * FROM students WHERE MATCH(first_name, middle_name, last_name) AGAINST('"+ searchPara + "' IN NATURAL LANGUAGE MODE)";
			pst=conn.prepareStatement(sql);
			rs = pst.executeQuery();
		//System.out.println("here");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Student s = new Student();
				String name = null;
				System.out.println("m name = " +rs.getString("middle_name"));
				try {
					if (rs.getString("middle_name")== null) {
						name = rs.getString("first_name") + " " +  rs.getString("last_name");
					} else {
						name = rs.getString("first_name") + " " + rs.getString("middle_name") + " " +rs.getString("last_name");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("id", rs.getInt("s_id"));
				map.put("name", name);
				map.put("image",rs.getString("image"));
				listOfReslut.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOfReslut;
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
			        	/*	
			        		Student model = new Student();
							model.setS_id(rs.getInt("s_id"));
							model.setAddress(rs.getString("address"));
							model.setCurrent_semester(rs.getInt("current_semester"));
							model.setDate_of_birth(rs.getString("date_of_birth"));
							model.setEmail(rs.getString("email"));
							model.setFirst_name(rs.getString("first_name"));
							model.setGender(rs.getInt("gender"));
							model.setImage(rs.getString("image"));
							model.setLast_name(rs.getString("last_name"));
							model.setMiddle_name(rs.getString("middle_name"));
							model.setPassword(rs.getString("password"));
							model.setPhone(rs.getString("phone"));
							model.setStatus(rs.getInt("status"));
							model.setUsername(rs.getString("username"));
						
							
						
			        					        		
			        		
							StudentsProgram model1 = new StudentsProgram();
							model1.setStudent_program_id(rs.getInt("student_program_id"));
							model1.setBatch_year(rs.getInt("batch_year"));
							model1.setEnroll_date(rs.getString("enroll_date"));
							model1.setStatus(rs.getInt("status"));
							model1.setProgram_id(rs.getInt("program_id"));
							model1.setS_id(rs.getInt("s_id"));
							
							studentsModel.add(model);
							studentsModel.add(model1);*/
			        		
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
