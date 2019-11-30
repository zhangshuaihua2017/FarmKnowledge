package com.farm.admin.controller;

import com.farm.admin.service.AdminService;
import com.farm.model.Admin;
import com.jfinal.core.Controller;

public class AdminController extends Controller{
	
	//��ת����ҳ��
	public void gotoIndex() {
		renderJsp("/index.jsp");
	}			
	
	//����Ա��½
	public void login() {
		String uName = get("uName");
		String password = get("password");
		AdminService service = new AdminService();
		boolean isExist = service.isExistAdmin(uName);
		if(isExist) {
			Admin admin = service.loginAdmin(uName, password);
			if(admin != null) {
				setSessionAttr("admin",admin);
				forwardAction("/admin/gotoIndex");
			}else {
				renderText("fail");
			}
		}else {
			renderText("notExist");
		}
	}
	
}
