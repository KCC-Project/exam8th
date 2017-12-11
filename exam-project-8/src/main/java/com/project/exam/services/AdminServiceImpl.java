package com.project.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.AdminDAO;
import com.project.exam.model.Admin;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDao;
	
	@Override
	public List<Admin> getAdminList() {
	return adminDao.getAdminList();
	}

	@Override
	public Admin addAdmin(Admin Admin) {
		return adminDao.addAdmin(Admin);
	}

	@Override
	public List<Admin> getAdmin(int s_Id) {
		return adminDao.getAdmin(s_Id);
	}

	@Override
	public Admin updateAdmin(Admin Admin) {
		return adminDao.updateAdmin(Admin);
	}

	@Override
	public int deleteAdmin(int s_Id) {
		return adminDao.deleteAdmin(s_Id);
	}

	@Override
	public List searchAdmin(String searchPara) {
	return adminDao.searchAdmin(searchPara);
	}

}
