package com.farm.admin.service;

import com.farm.admin.dao.AdminDao;
import com.farm.model.Admin;

public class AdminService {
	
	//�鿴�Ƿ���ڸù���Ա��Admin��
	public boolean isExistAdmin(String uName) {
		return new AdminDao().isExistAdmin(uName);
	}
	
	//��֤����Ա�ĵ�½�˺š����루��
	public Admin loginAdmin(String uName,String password) {
		return new AdminDao().loginAdmin(uName,password);
	}
}
