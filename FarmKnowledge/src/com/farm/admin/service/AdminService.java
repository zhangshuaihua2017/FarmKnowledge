package com.farm.admin.service;

import com.farm.admin.dao.AdminDao;
import com.farm.model.Admin;
import com.jfinal.plugin.activerecord.Page;

public class AdminService {
	
	//�鿴�Ƿ���ڸù���Ա��Admin��
	public boolean isExistAdmin(String accout) {
		return new AdminDao().isExistAdmin(accout);
	}
	
	//��֤����Ա�ĵ�½�˺š����루Admin��
	public Admin loginAdmin(String accout,String password) {
		return new AdminDao().loginAdmin(accout,password);
	}
	
	//��ѯAdmin�������й���Ա��Ϣ��Admin��
	public Page<Admin> findAdminPage(int currentPage,int everyCount,String accout,int exist) {
		return new AdminDao().findAdminPage(currentPage,everyCount,accout,exist);
	}	
	
}
