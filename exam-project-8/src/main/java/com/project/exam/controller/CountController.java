package com.project.exam.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.CountInfo;
import com.project.exam.services.CountService;

@Path("/ApiCount")
public class CountController {

	@Autowired
	private CountService countService;

	@GET
	@Path("/Student")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<CountInfo> getAllCounts() {
		return countService.getAllCounts();
	}
	
	
}
