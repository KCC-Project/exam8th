package com.project.exam.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ForgetPasswordController {

	
	@GET
	@Path("/forgotPassword")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void login() {
		System.out.println("it is here..!!!");
	
}
}
