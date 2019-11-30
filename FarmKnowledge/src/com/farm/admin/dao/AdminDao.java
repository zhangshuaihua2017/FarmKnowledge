package com.farm.admin.dao;

import java.util.List;

import com.farm.model.Admin;
import com.jfinal.plugin.activerecord.Page;

public class AdminDao {
	
	//查看是否存在该管理员（Admin表）
	public boolean isExistAdmin(String accout) {
		List<Admin> list = Admin.dao.find("select * from admin where accout=?",accout);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}

	//验证管理员的登陆账号、密码（Admin表）
	public Admin loginAdmin(String accout,String password) {
		List<Admin> list = Admin.dao.find("select * from admin where accout=? and password=?",accout,password);
		if(list.size() != 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	//查询User表内所有用户信息（User表）
	public Page<Admin> findAdminPage(int currentPage,int everyCount,String accout,int exist) {
		Page<Admin> adminPage;
		if(accout == null || accout.equals("")) {
			adminPage = Admin.dao.paginate(currentPage, everyCount, "select *","from admin where exist=?",exist);	
		}else {
			adminPage = Admin.dao.paginate(currentPage, everyCount, "select *","from admin where exist=? and accout like?",exist,"%"+accout+"%");
		}
		return adminPage;
	}	

}
