package com.farm.admin.controller;

import com.farm.admin.service.AdminService;
import com.farm.model.Admin;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class AdminController extends Controller{
	
	//��ת����ҳ��
	public void gotoIndex() {
		renderJsp("/index.jsp");
	}			
	
	//����Ա��½
	public void login() {
		String accout = get("accout");
		String password = get("password");
		AdminService service = new AdminService();
		boolean isExist = service.isExistAdmin(accout);
		if(isExist) {
			Admin admin = service.loginAdmin(accout, password);
			if(admin != null) {
				setSessionAttr("admin",admin);
				renderText("succeed");
			}else {
				renderText("fail");
			}
		}else {
			renderText("notExist");
		}
	}
	
	//��ѯAdmin�������й���Ա��Ϣ����ת������Ա�б�ҳ�棨Admin��
	public void findAdminPage() {
		String accout = get("accout");
		String page = get("pageNumber");
		String count = get("pageSize");
		int exist = getInt("exist");
		int pageNumber;
		int everyCount;
		
		if(page == null) {
			pageNumber = 1;
		}else {
			pageNumber = Integer.parseInt(page);
		}
		if(count == null) {
			everyCount = 1;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<Admin> list = new AdminService().findAdminPage(pageNumber,everyCount,accout,exist);
		setAttr("adminPage", list);
		if(exist == 1) {
			renderJsp("/admin-list.jsp");
		}else {
			renderJsp("/admin-del.jsp");
		}
	}
	
}
