package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.MaxFailSubjectViewDAO;
import com.project.exam.model.MaxFailSubjectView;

@Service
public class MaxFailSubjectViewServiceImpl implements MaxFailSubjectViewService {

	@Autowired
	private MaxFailSubjectViewDAO fail;
	
	@Override
	public List<MaxFailSubjectView> getAllRecords() {
		
		return fail.getAllRecords();
	}

}
