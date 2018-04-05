package com.project.exam.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.exam.model.FailRatioCountView;
import com.project.exam.services.FailRatioCountViewService;

@Path("/ApiViewController")
public class ViewController {
	
	@Autowired
	private FailRatioCountViewService failRatioCountViewService;
	
	@GET
	@Path("/FailRatioCountView")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<FailRatioCountView> getAllCountInfo() {
		return failRatioCountViewService.getAllCounts();
	}

}
