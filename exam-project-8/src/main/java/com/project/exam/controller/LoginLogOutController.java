package com.project.exam.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.exam.model.Admin;
import com.project.exam.model.Student;
import com.project.exam.services.AdminService;
import com.project.exam.services.StudentService;

@Path("/")
@SessionAttributes("userName")
public class LoginLogOutController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private StudentService studentService;

	@POST
	@Path("/ApiLoginOut")
	public Response login(@FormParam("category") String category, @FormParam("InputEmail1User") String InputEmail1User,
			@FormParam("InputPassword1") String InputPassword1, @FormParam("rememberMe") String rememberMe
			,@Context HttpServletRequest req) throws URISyntaxException  {
		
		int categoryIndex = 0;
		
		if (category.equalsIgnoreCase("Admin")) {
			categoryIndex = 1;
		} else if (category.equalsIgnoreCase("Student")) {
			categoryIndex = 3;
		}
		if (categoryIndex == 1) {
			List<Admin> admin = adminService.getAdminList();
			for (Admin admin2 : admin) {
				if (admin2.getAdmin_username().equalsIgnoreCase(InputEmail1User)
						&& admin2.getAdmin_password().equals(InputPassword1)) {
					
					//for session only if remember btn is checked
					HttpSession session= req.getSession(true);
					session.setAttribute("adminUserName", InputEmail1User);
					if (rememberMe != null) {
						session.setAttribute("adminPassword", InputPassword1);
				
					}
		
					System.out.println("Admin login sucess");
					URI targetURIForRedirection = new URI("home");
					return Response.seeOther(targetURIForRedirection).build();
				}
			}
		}
		if (categoryIndex == 3) {

			List<Student> student = studentService.getStudentList();
			for (Student student1 : student) {
				if (student1.getUsername().equalsIgnoreCase(InputEmail1User)
						&& student1.getPassword().equals(InputPassword1)) {
					HttpSession session= req.getSession(true);
					session.setAttribute("studentUserName", InputEmail1User);
					session.setAttribute("studentID", student1.getS_id());
					session.setAttribute("first_name", student1.getFirst_name());
					session.setAttribute("middle_name", student1.getMiddle_name());
					session.setAttribute("last_name", student1.getLast_name());
					if (student1.getGender()==0) {
						session.setAttribute("gender", "Male");
					}else {
						session.setAttribute("gender", "Female");
					}
					
					session.setAttribute("dob", student1.getDate_of_birth());
					session.setAttribute("email", student1.getEmail());
					session.setAttribute("status", student1.getStatus());
					session.setAttribute("phone", student1.getPhone());
					session.setAttribute("password", student1.getPassword());
					//for session only if remember btn is checked
					if (rememberMe != null) {
					
						session.setAttribute("studentPassword", InputPassword1);
					}
			
					System.out.println("Student login sucess");
					URI targetURIForRedirection = new URI("user");
					return Response.seeOther(targetURIForRedirection).build();
				}
			}
		}
		return null;
	}

	@GET
	@Path("/logoutAdmin")
	public Response logoutAdmin(@Context HttpServletRequest req) throws URISyntaxException {
		HttpSession session= req.getSession(true);
		session.removeAttribute("adminUserName");
		StringBuffer requestURL = req.getRequestURL();
		System.out.println("full url="+requestURL);
		String uri = req.getScheme() + "://" +   // "http" + "://
				req.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             req.getServerPort() + "/exam-project-7/";     
	           
		// req.getRequestURI() ;    
		System.out.println("url ="+uri);
		URI targetURIForRedirection = new URI(uri);
		return Response.seeOther(targetURIForRedirection).build();
	
		
	}
	
	@GET
	@Path("/logoutStudent")
	public Response logoutStudent(@Context HttpServletRequest req) throws URISyntaxException {
		HttpSession session= req.getSession(true);
		session.removeAttribute("studentUserName");
		String uri = req.getScheme() + "://" +   // "http" + "://
				req.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             req.getServerPort() + "/exam-project-7/";   
		URI targetURIForRedirection = new URI(uri);
		return Response.seeOther(targetURIForRedirection).build();
		
	}
}
