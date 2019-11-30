package com.farm.admin.dao;

import java.util.List;

import com.farm.model.Admin;

public class AdminDao {
	
	//查看是否存在该管理员（Admin表）
	public boolean isExistAdmin(String uName) {
		List<Admin> list = Admin.dao.find("select * from admin where uName=?",uName);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}

	//验证管理员的登陆账号、密码（Admin表）
	public Admin loginAdmin(String uName,String password) {
		List<Admin> list = Admin.dao.find("select * from admin where uName=? and password=?",uName,password);
		if(list.size() != 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
