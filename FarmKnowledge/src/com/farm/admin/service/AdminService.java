package com.farm.admin.service;

import com.farm.admin.dao.AdminDao;
import com.farm.model.Admin;
import com.jfinal.plugin.activerecord.Page;

public class AdminService {
	
	//查看是否存在该管理员（Admin表）
	public boolean isExistAdmin(String accout) {
		return new AdminDao().isExistAdmin(accout);
	}
	
	//验证管理员的登陆账号、密码（Admin表）
	public Admin loginAdmin(String accout,String password) {
		return new AdminDao().loginAdmin(accout,password);
	}
	
	//查询Admin表内所有管理员信息（Admin表）
	public Page<Admin> findAdminPage(int currentPage,int everyCount,String accout,int exist) {
		return new AdminDao().findAdminPage(currentPage,everyCount,accout,exist);
	}	
	
}
