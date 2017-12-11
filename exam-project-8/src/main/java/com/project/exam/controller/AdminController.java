package com.project.exam.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.Admin;
import com.project.exam.services.AdminService;

@Path("/ApiAdmin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GET
	@Path("/GetAllAdmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Admin> getAllAdmin() {
		return adminService.getAdminList();
	}
	
	
	@POST
	@Path("/SaveAdmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Admin saveAdmin(Admin admin) {
		return adminService.addAdmin(admin);
	}
	

	@GET
	@Path("/GetAdmin/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Admin> getAdmin(@PathParam("id") int id) {
		return adminService.getAdmin(id);
	}
	
	@PUT
	@Path("/UpdateAdmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Admin updateAdmin(Admin admin) {
		return adminService.updateAdmin(admin);
	}
	
	@DELETE
	@Path("/DeleteAdmin/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public int deleteAdmin(@PathParam("id") int id) {
		return adminService.deleteAdmin(id);
	}
	@POST
	@Path("/SearchAdmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List searchProgram(@FormParam("val") String params) {
		System.out.println("inside search SearchAdmin ");
		//System.out.println("from controller= "+studentService.searchStudent(params).toString());
	return adminService.searchAdmin(params);
		
	}
}
