package com.farm.admin.controller;

import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	//跳转到用户列表页面，查询出所有用户
	public void findAllUser() {
		renderJsp("/member-list.jsp");
	}
}
