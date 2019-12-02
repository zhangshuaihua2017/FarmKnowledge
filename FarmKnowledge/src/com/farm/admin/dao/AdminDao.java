package com.farm.admin.dao;

import java.util.List;

import com.farm.model.Admin;
import com.jfinal.plugin.activerecord.Page;

public class AdminDao {
	
	//�鿴�Ƿ���ڸù���Ա��Admin��
	public boolean isExistAdmin(String accout) {
		List<Admin> list = Admin.dao.find("select * from admin where accout=? and exist=1",accout);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}

	//��֤����Ա�ĵ�½�˺š����루Admin��
	public Admin loginAdmin(String accout,String password) {
		List<Admin> list = Admin.dao.find("select * from admin where accout=? and password=? and exist=1",accout,password);
		if(list.size() != 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	//��ѯAdmin�������й���Ա��Ϣ��Admin��
	public Page<Admin> findAdminPage(int pageNumber,int everyCount,String accout,int exist) {
		Page<Admin> adminPage;
		if(accout == null || accout.equals("")) {
			adminPage = Admin.dao.paginate(pageNumber, everyCount, "select *","from admin where exist=?",exist);	
		}else {
			adminPage = Admin.dao.paginate(pageNumber, everyCount, "select *","from admin where exist=? and accout like?",exist,"%"+accout+"%");
		}
		return adminPage;
	}	

}
