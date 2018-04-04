package com.project.exam.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.exam.model.Admin;
import com.project.exam.model.ForgetPasswordModel;
import com.project.exam.model.Student;
import com.project.exam.services.AdminService;
import com.project.exam.services.ForgetPasswordService;
import com.project.exam.services.StudentService;
import com.project.exam.util.MailUtil;

@Path("/")
@SessionAttributes("userName")
public class LoginLogOutController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private StudentService studentService;

	@Autowired
	ForgetPasswordService forgetPassword;

	// this method validated if the url of gmail is valid or not

	@GET
	@Path("/ApiResetPassword/{vCode}/{tablename}/{id}")
	public boolean resetPassword(@PathParam("vCode") String vCode, @PathParam("tablename") String tablename,
			@PathParam("id") int id) {

		boolean isAuthenticated = forgetPassword.isAuthenticated(tablename, id, vCode);

		return isAuthenticated;

	}

	// this method reset the password

	@POST
	@Path("/ApiResetPasswordNext")
	public int resetPassword(String json) {

		boolean status = false;

		int responseCode = -400;

		String vCode = null;

		String tablename = null;

		String password = null;

		String s = null;

		int id = 0;

		if (json.charAt(0) == '{') {

			s = "[" + json + "]";

		} else {

			s = json;

		}

		JSONArray jsonArray = new JSONArray(s);

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);

			vCode = jsonObject.getString("vCode");

			tablename = jsonObject.getString("tablename");

			password = jsonObject.getString("password");

			id = jsonObject.getInt("ID");
		}

		boolean isAuthenticated = forgetPassword.isAuthenticated(tablename, id, vCode);

		if (isAuthenticated) {

			status = forgetPassword.resetPassword(tablename, id, vCode, password);

		}

		if (status) {

			responseCode = 200;
		}

		return responseCode;

	}

	@GET
	@Path("/ApiForgetPasswordTimeLimiter/{email}")
	public void forgetPasswordTimeLimiter(@PathParam("email") String email)
			throws AddressException, javax.mail.MessagingException {

		ForgetPasswordModel emailModel = forgetPassword.forgetPasswordCheckEmail(email);

	}

	@GET
	@Path("/ApiForgetPassword/{email}")
	public String forgetPassword(@PathParam("email") String email)
			throws AddressException, javax.mail.MessagingException {

		String message = "failed";

		ForgetPasswordModel emailModel = forgetPassword.forgetPasswordCheckEmail(email);

		//try catch is neede if email is not returned in emailModel  and checking to null model
		
		try {
		
		if (!emailModel.getEmailOfUser().equalsIgnoreCase(email)) {

			message = "Invalid Email! please use your valid email address to restore password";

			return message;

		} else if (emailModel.getEmailOfUser().equalsIgnoreCase(email)) {

			MailUtil.sendEmailPasswordForgot(emailModel.getEmailOfUser(), emailModel.getAuthienciationCodeOfUser(),
					emailModel.getIdOfUser(), emailModel.getTypeOfUser());

			message = "Success! Please check you email for the verification Link. <a href='https://www.google.com/gmail/' target='_blank'>click here</a>";

			final String email1=email;
			
			new Timer().schedule(

				    new TimerTask() {
				        @Override
				        public void run() {
				           
				        	forgetPassword.forgetPasswordCheckEmail(email1);
				        }
				    }, 
				    3600000
				);
			
			return message;

		}
		}catch(Exception e) {
			
			System.out.println("******************  Email  does not match with our database   **************************");
			
		}

		return message;
	}

	@POST
	@Path("/ApiLoginOut")
	public int login(String json, @Context HttpServletRequest req) throws URISyntaxException {

		String category = null;
		String InputEmail1User = null;
		String InputPassword1 = null;
		String s = null;

		int id;

		if (json.charAt(0) == '{') {
			s = "[" + json + "]";
		} else {
			s = json;
		}

		// System.out.println(json);

		// System.out.println("s = "+s);

		JSONArray jsonArray = new JSONArray(s);

		System.out.println("jsonArray = " + jsonArray);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			category = jsonObject.getString("category");
			InputEmail1User = jsonObject.getString("adminUserName");
			InputPassword1 = jsonObject.getString("InputPassword1");
		}

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
						&& admin2.getPassword().equals(InputPassword1)) {

					id = admin2.getAdmin_id();

					// for session only if remember btn is checked

					HttpSession session = req.getSession(true);

					session.setAttribute("adminUserName", InputEmail1User);

					session.setAttribute("adminPassword", InputPassword1);

					System.out.println("Admin login sucess  & id =" + id);

					return id;
				}
			}
		}
		if (categoryIndex == 3) {

			List<Student> student = studentService.getStudentList();
			for (Student student1 : student) {
				if (student1.getUsername().equalsIgnoreCase(InputEmail1User)
						&& student1.getPassword().equals(InputPassword1)) {

					id = student1.getS_id();

					HttpSession session = req.getSession(true);
					session.setAttribute("studentUserName", InputEmail1User);
					session.setAttribute("studentID", student1.getS_id());
					session.setAttribute("first_name", student1.getFirst_name());
					session.setAttribute("middle_name", student1.getMiddle_name());
					session.setAttribute("last_name", student1.getLast_name());
					if (student1.getGender() == 0) {
						session.setAttribute("gender", "Male");
					} else {
						session.setAttribute("gender", "Female");
					}

					session.setAttribute("dob", student1.getDate_of_birth());
					session.setAttribute("email", student1.getEmail());
					session.setAttribute("status", student1.getStatus());
					session.setAttribute("phone", student1.getPhone());
					session.setAttribute("password", student1.getPassword());
					// for session only if remember btn is checked

					session.setAttribute("studentPassword", InputPassword1);
					session.setAttribute("currentSemester", student1.getCurrent_semester());

					System.out.println("Student login sucess & id = " + id);
					return id;
				}
			}
		}
		return 0;

	}

	@GET
	@Path("/logoutAdmin")
	public Response logoutAdmin(@Context HttpServletRequest req) throws URISyntaxException {
		HttpSession session = req.getSession(true);
		session.removeAttribute("adminUserName");
		StringBuffer requestURL = req.getRequestURL();
		System.out.println("full url=" + requestURL);
		String uri = req.getScheme() + "://" + // "http" + "://
				req.getServerName() + // "myhost"
				":" + // ":"
				req.getServerPort() + "/exam-project-8/";

		// req.getRequestURI() ;
		System.out.println("url =" + uri);
		URI targetURIForRedirection = new URI(uri);
		return Response.seeOther(targetURIForRedirection).build();

	}

	@GET
	@Path("/logoutStudent")
	public Response logoutStudent(@Context HttpServletRequest req) throws URISyntaxException {
		HttpSession session = req.getSession(true);
		session.removeAttribute("studentUserName");
		String uri = req.getScheme() + "://" + // "http" + "://
				req.getServerName() + // "myhost"
				":" + // ":"
				req.getServerPort() + "/exam-project-8/";
		URI targetURIForRedirection = new URI(uri);
		return Response.seeOther(targetURIForRedirection).build();

	}

}
