package admin.service;

import admin.dao.AdminDao;
import model.Admin;

public class AdminService {
	
	//查看是否存在该管理员（Admin表）
	public boolean isExistAdmin(String uName) {
		return new AdminDao().isExistAdmin(uName);
	}
	
	//验证管理员的登陆账号、密码（）
	public Admin loginAdmin(String uName,String password) {
		return new AdminDao().loginAdmin(uName,password);
	}
}
