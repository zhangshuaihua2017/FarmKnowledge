package com.farm.admin.controller;

import com.farm.admin.service.AdminService;
import com.farm.model.Admin;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class AdminController extends Controller{
	
	//跳转到主页面
	public void gotoIndex() {
		renderJsp("/index.jsp");
	}			
	
	//管理员登陆
	public void login() {
		String accout = get("accout");
		String password = get("password");
		AdminService service = new AdminService();
		boolean isExist = service.isExistAdmin(accout);
		if(isExist) {
			Admin admin = service.loginAdmin(accout, password);
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
	
	//查询Admin表内所有管理员信息（Admin表），跳转到管理员列表页面，
	public void findAdminPage() {
		String accout = get("accout");
		String page = get("currentPage");
		String count = get("pageSize");
		int exist = getInt("exist");
		int currentPage;
		int everyCount;
		
		if(page == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(page);
		}
		if(count == null) {
			everyCount = 1;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<Admin> list = new AdminService().findAdminPage(currentPage,everyCount,accout,exist);
		setAttr("adminPage", list);
		if(exist == 1) {
			renderJsp("/admin-list.jsp");
		}else {
			renderJsp("/admin-del.jsp");
		}
	}
	
}
