package com.farm.admin.controller;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller{
	
	//查询User表内用户信息（User表），跳转到用户列表页面，
	public void findUserPage() {
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
			everyCount = 2;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<User> list = new UserService().findUserPage(currentPage,everyCount,accout,exist);
		setAttr("userPage", list);
		if(exist == 1) {
			renderJsp("/member-list.jsp");
		}else {
			renderJsp("/member-del.jsp");
		}
		
	}

}
