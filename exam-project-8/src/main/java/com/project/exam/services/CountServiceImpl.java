package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.CountDao;
import com.project.exam.model.CountInfo;

@Service
public class CountServiceImpl implements CountService{

	@Autowired
	private CountDao countDao;

	@Override
	public List<CountInfo> getAllCounts() {
		return countDao.getAllCounts();
	}

}
