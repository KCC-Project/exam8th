package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.CountDao;
import com.project.exam.dao.FailRatioCountViewDAO;
import com.project.exam.model.FailRatioCountView;

@Service
public class FailRatioCountViewServiceImpl implements FailRatioCountViewService {

	@Autowired
	private FailRatioCountViewDAO failRatioCountViewDAO;

	
	@Override
	public List<FailRatioCountView> getAllCounts() {
		
		return failRatioCountViewDAO.getAllCounts();
	}

}
