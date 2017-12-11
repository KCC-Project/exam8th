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
import com.project.exam.model.Subjects;

@Repository("subjectDao")
public class SubjectDAOImpl implements SubjectDAO {
	private Connection conn;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Subjects> getallSubjectList() {
		List<Subjects> listSubjects = new ArrayList<Subjects>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from subjects";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Subjects model = new Subjects();
				model.setSubject_id(rs.getInt("subject_id"));
				model.setFinal_theory(rs.getInt("final_theory"));
				model.setInternal_practical(rs.getInt("internal_practical"));
				model.setInternal_theory(rs.getInt("internal_theory"));
				model.setSemester_no(rs.getInt("semester_no"));
				model.setStatus(rs.getInt("status"));
				model.setSubject_code(rs.getString("subject_code"));
				model.setSubject_name(rs.getString("subject_name"));
				model.setSyllabus_file(rs.getString("syllabus_file"));
				model.setTheory_cr(rs.getInt("theory_cr"));
				model.setTutorial_cr(rs.getInt("tutorial_cr"));
				model.setProgram_id(rs.getInt("program_id"));
				listSubjects.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listSubjects;
	}

	@Override
	public Subjects addStudent(Subjects subject) {
		boolean status = false;

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "insert into subjects(final_theory,internal_practical,internal_theory,semester_no,status,subject_code,subject_name,"
					+ "syllabus_file,theory_cr,tutorial_cr,program_id) values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int col = 1;

			pst.setInt(col++, subject.getFinal_theory());
			pst.setInt(col++, subject.getInternal_practical());
			pst.setInt(col++, subject.getInternal_theory());
			pst.setInt(col++, subject.getSemester_no());
			pst.setInt(col++, subject.getStatus());
			pst.setString(col++, subject.getSubject_code());
			pst.setString(col++, subject.getSubject_name());
			pst.setString(col++, subject.getSyllabus_file());
			pst.setInt(col++, subject.getTheory_cr());
			pst.setInt(col++, subject.getTutorial_cr());
			pst.setInt(col++, subject.getProgram_id());

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
			return subject;
		}
		return new Subjects();
	}

	@Override
	public List<Subjects> getSubject(int s_Id) {
		List<Subjects> listSubject= new ArrayList<>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "Select * from subjects where subject_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s_Id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Subjects model = new Subjects();
				model.setSubject_id(rs.getInt("subject_id"));
				model.setFinal_theory(rs.getInt("final_theory"));
				model.setInternal_practical(rs.getInt("internal_practical"));
				model.setInternal_theory(rs.getInt("internal_theory"));
				model.setSemester_no(rs.getInt("semester_no"));
				model.setStatus(rs.getInt("status"));
				model.setSubject_code(rs.getString("subject_code"));
				model.setSubject_name(rs.getString("subject_name"));
				model.setSyllabus_file(rs.getString("syllabus_file"));
				model.setTheory_cr(rs.getInt("theory_cr"));
				model.setTutorial_cr(rs.getInt("tutorial_cr"));
				model.setProgram_id(rs.getInt("program_id"));
				listSubject.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listSubject;
	}

	@Override
	public Subjects updateSubject(Subjects subject) {

		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "update subjects set final_theory=?,internal_practical=?,internal_theory=?,semester_no=?,status=?,subject_code=?,subject_name=?,syllabus_file=?,theory_cr=?,tutorial_cr=?,program_id=? where subject_id=?";
			pst = conn.prepareStatement(sql);
			int col = 1;
			pst.setInt(col++, subject.getFinal_theory());
			pst.setInt(col++, subject.getInternal_practical());
			pst.setInt(col++, subject.getInternal_theory());
			pst.setInt(col++, subject.getSemester_no());
			pst.setInt(col++, subject.getStatus());
			pst.setString(col++, subject.getSubject_code());
			pst.setString(col++, subject.getSubject_name());
			pst.setString(col++, subject.getSyllabus_file());
			pst.setInt(col++, subject.getTheory_cr());
			pst.setInt(col++, subject.getTutorial_cr());
			pst.setInt(col++, subject.getProgram_id());
			pst.setInt(col++, subject.getSubject_id());
			int count = pst.executeUpdate();
			if (count > 0) {

				return subject;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Subjects();
	}

	@Override
	public int deleteSubject(int s_Id) {
		int result = 0;
		// System.out.println("deleting id form ecaminfoModel="+id);
		try {
			Connection connection = DatabaseConnection.connectToDatabase();
			sql = "delete from subjects where subject_id =?";
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
	public List searchSubject(String searchPara) {
		
		List listOfResult = new ArrayList<>();
		try {
			conn = DatabaseConnection.connectToDatabase();
			sql = "SELECT * FROM subjects WHERE subject_name like '" + searchPara + "%'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Subjects s = new Subjects();
				String subjectName = null;
				subjectName = rs.getString("subject_name") + " " + rs.getString("subject_code");

				map.put("id", rs.getInt("subject_id"));
				map.put("name", subjectName);
				listOfResult.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOfResult;
	}

	@Override
	public List<Subjects> getSubjectByParameters(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List<Subjects> listSubjects = new ArrayList<Subjects>();

		int subject_id = 0;
		int program_id = 0;
		String subject_name = null;
		String subject_code = null;
		int theory_cr = 0;
		int tutorial_cr = 0;
		int internal_theory = 0;
		int internal_practical = 0;
		int final_theory = 0;
		int semester_no = 0;
		int status = 0;


		if (obj[0] != null) {  subject_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  program_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  subject_name = obj[2].toString(); }
		if (obj[3] != null) {  subject_code = obj[3].toString(); }
		if (obj[4] != null) {  theory_cr = Integer.parseInt(obj[4].toString()); }
		if (obj[5] != null) {  tutorial_cr = Integer.parseInt(obj[5].toString()); }
		if (obj[6] != null) {  internal_theory = Integer.parseInt(obj[6].toString()); }
		if (obj[7] != null) {  internal_practical = Integer.parseInt(obj[7].toString()); }
		if (obj[8] != null) {  final_theory = Integer.parseInt(obj[8].toString()); }
		if (obj[9] != null) {  semester_no = Integer.parseInt(obj[9].toString()); }
		if (obj[10] != null) {  status = Integer.parseInt(obj[10].toString()); }
		
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM subjects where 1=1");
			  
				if (subject_id != 0) {
		            query.append(" AND subject_id = ?");
		            parameters.add(subject_id);
		        }
				if (program_id != 0) {
		            query.append(" AND program_id = ?");
		            parameters.add(program_id);
		        }
				if (subject_name != null) {
		            query.append(" AND subject_name = ?");
		            parameters.add(subject_name);
		        }
				if (subject_code != null) {
		            query.append(" AND subject_code = ?");
		            parameters.add(subject_code);
		        }
				if (theory_cr != 0) {
		            query.append(" AND theory_cr = ?");
		            parameters.add(theory_cr);
		        }
				if (tutorial_cr != 0) {
		            query.append(" AND tutorial_cr = ?");
		            parameters.add(tutorial_cr);
		        }
				if (internal_theory != 0) {
		            query.append(" AND internal_theory = ?");
		            parameters.add(internal_theory);
		        }
				if (internal_practical != 0) {
		            query.append(" AND internal_practical = ?");
		            parameters.add(internal_practical);
		        }
				if (final_theory != 0) {
		            query.append(" AND final_theory = ?");
		            parameters.add(final_theory);
		        }
				if (semester_no != 0) {
		            query.append(" AND semester_no = ?");
		            parameters.add(semester_no);
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
			        		/*
			        		Map<String, Object> map= new HashMap<>();
			        		map.put("subject_id", rs.getInt("subject_id"));
			        		map.put("subject_name", rs.getString("subject_name"));
			        		map.put("subject_code", rs.getInt("subject_code"));
			        		map.put("theory_cr",rs.getString("theory_cr") );
			        		map.put("tutorial_cr", rs.getString("tutorial_cr"));
			        		map.put("internal_theory",rs.getString("internal_theory") );
			        		map.put("internal_practical", rs.getInt("internal_practical"));		
			        		map.put("final_theory", rs.getString("final_theory"));
			        		map.put("semester_no", rs.getString("semester_no"));
			        		map.put("status", rs.getString("status"));
			        		
			        		subjectModel.add(map);
			        		*/
			        		Subjects model = new Subjects();
							model.setSubject_id(rs.getInt("subject_id"));
							model.setProgram_id(rs.getInt("program_id"));
							model.setFinal_theory(rs.getInt("final_theory"));
							model.setInternal_practical(rs.getInt("internal_practical"));
							model.setInternal_theory(rs.getInt("internal_theory"));
							model.setSemester_no(rs.getInt("semester_no"));
							model.setStatus(rs.getInt("status"));
							model.setSubject_code(rs.getString("subject_code"));
							model.setSubject_name(rs.getString("subject_name"));
							model.setSyllabus_file(rs.getString("syllabus_file"));
							model.setTheory_cr(rs.getInt("theory_cr"));
							model.setTutorial_cr(rs.getInt("tutorial_cr"));
							listSubjects.add(model);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Arrays.deepToString(listSubjects.toArray()));
		return listSubjects;

	}

	@Override
	public List getSubjectByProgram(Object[] obj) {
		List<Object> parameters = new ArrayList<Object>();
		List listSubjects = new ArrayList<Subjects>();

		int subject_id = 0;
		int program_id = 0;
		String subject_name = null;
		String subject_code = null;
		int theory_cr = 0;
		int tutorial_cr = 0;
		int internal_theory = 0;
		int internal_practical = 0;
		int final_theory = 0;
		int semester_no = 0;
		int status = 0;


		if (obj[0] != null) {  subject_id = Integer.parseInt(obj[0].toString()); }
		if (obj[1] != null) {  program_id = Integer.parseInt(obj[1].toString()); }
		if (obj[2] != null) {  subject_name = obj[2].toString(); }
		if (obj[3] != null) {  subject_code = obj[3].toString(); }
		if (obj[4] != null) {  theory_cr = Integer.parseInt(obj[4].toString()); }
		if (obj[5] != null) {  tutorial_cr = Integer.parseInt(obj[5].toString()); }
		if (obj[6] != null) {  internal_theory = Integer.parseInt(obj[6].toString()); }
		if (obj[7] != null) {  internal_practical = Integer.parseInt(obj[7].toString()); }
		if (obj[8] != null) {  final_theory = Integer.parseInt(obj[8].toString()); }
		if (obj[9] != null) {  semester_no = Integer.parseInt(obj[9].toString()); }
		if (obj[10] != null) {  status = Integer.parseInt(obj[10].toString()); }
		
		
		try {
			  StringBuilder query = new StringBuilder("SELECT * FROM subjects as s INNER JOIN programs as p ON s.program_id = p.program_id where 1=1");
			  
				if (subject_id != 0) {
		            query.append(" AND subject_id = ?");
		            parameters.add(subject_id);
		        }
				if (program_id != 0) {
		            query.append(" AND s.program_id = ?");
		            parameters.add(program_id);
		        }
				if (subject_name != null) {
		            query.append(" AND subject_name = ?");
		            parameters.add(subject_name);
		        }
				if (subject_code != null) {
		            query.append(" AND subject_code = ?");
		            parameters.add(subject_code);
		        }
				if (theory_cr != 0) {
		            query.append(" AND theory_cr = ?");
		            parameters.add(theory_cr);
		        }
				if (tutorial_cr != 0) {
		            query.append(" AND tutorial_cr = ?");
		            parameters.add(tutorial_cr);
		        }
				if (internal_theory != 0) {
		            query.append(" AND internal_theory = ?");
		            parameters.add(internal_theory);
		        }
				if (internal_practical != 0) {
		            query.append(" AND internal_practical = ?");
		            parameters.add(internal_practical);
		        }
				if (final_theory != 0) {
		            query.append(" AND final_theory = ?");
		            parameters.add(final_theory);
		        }
				if (semester_no != 0) {
		            query.append(" AND semester_no = ?");
		            parameters.add(semester_no);
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
			        		/*
			        		Map<String, Object> map= new HashMap<>();
			        		map.put("subject_id", rs.getInt("subject_id"));
			        		map.put("subject_name", rs.getString("subject_name"));
			        		map.put("subject_code", rs.getInt("subject_code"));
			        		map.put("theory_cr",rs.getString("theory_cr") );
			        		map.put("tutorial_cr", rs.getString("tutorial_cr"));
			        		map.put("internal_theory",rs.getString("internal_theory") );
			        		map.put("internal_practical", rs.getInt("internal_practical"));		
			        		map.put("final_theory", rs.getString("final_theory"));
			        		map.put("semester_no", rs.getString("semester_no"));
			        		map.put("status", rs.getString("status"));
			        		
			        		subjectModel.add(map);
			        		*/
			        		
			        		Map<String, Object> map= new HashMap<>();
			        		
							map.put("subject_id",rs.getInt("subject_id"));
							map.put("program_id",rs.getInt("program_id"));
							map.put("final_theory",rs.getInt("final_theory"));
							map.put("internal_practical",rs.getInt("internal_practical"));
							map.put("internal_theory",rs.getInt("internal_theory"));
							map.put("semester_no",rs.getInt("semester_no"));
							map.put("status",rs.getInt("status"));
							map.put("subject_code",rs.getString("subject_code"));
							map.put("subject_name",rs.getString("subject_name"));
							map.put("syllabus_file",rs.getString("syllabus_file"));
							map.put("theory_cr",rs.getInt("theory_cr"));
							map.put("tutorial_cr",rs.getInt("tutorial_cr"));
							
							
							map.put("program_name",rs.getInt("program_name"));
							listSubjects.add(map);
						}

			        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Arrays.deepToString(listSubjects.toArray()));
		return listSubjects;
	}

}
