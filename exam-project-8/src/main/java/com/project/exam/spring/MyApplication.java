package com.project.exam.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletProperties;

import com.project.exam.controller.AdminController;
import com.project.exam.controller.ExamController;
import com.project.exam.controller.Exam_typeController;
import com.project.exam.controller.FacultyController;
import com.project.exam.controller.LoginLogOutController;
import com.project.exam.controller.MainController;
import com.project.exam.controller.ProgramController;
import com.project.exam.controller.StudentController;
import com.project.exam.controller.StudentsExamController;
import com.project.exam.controller.StudentsProgramController;
import com.project.exam.controller.SubjectController;

public class MyApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MyApplication() {

		register(JspMvcFeature.class);
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/jsp");
		property(ServletProperties.FILTER_STATIC_CONTENT_REGEX,
				// "/(images|css)/.*");
				"/css/*");

		register(MainController.class);
		register(StudentController.class);
		register(SubjectController.class);
		register(ProgramController.class);
		register(FacultyController.class);
		register(AdminController.class);
		register(Exam_typeController.class);
		register(StudentsExamController.class);
		register(ExamController.class);
		register(StudentsProgramController.class);
		register(LoginLogOutController.class);
		
	}
}
