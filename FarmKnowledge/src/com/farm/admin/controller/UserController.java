package com.farm.admin.controller;

import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	//��ת���û��б�ҳ�棬��ѯ�������û�
	public void findAllUser() {
		renderJsp("/member-list.jsp");
	}
}
