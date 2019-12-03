package com.farm.admin.controller;

import java.sql.SQLException;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller{
	
	//��ѯUser�����û���Ϣ����ת���û��б�ҳ�棨User��
	public void findUserPage() {
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
			everyCount = 4;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<User> list = new UserService().findUserPage(pageNumber,everyCount,accout,exist);
		setAttr("userPage", list);
		if(exist == 1) {
			renderJsp("/member-list.jsp");
		}else {
			renderJsp("/member-del.jsp");
		}
		
	}

	//ɾ��User���ڵ����û���Ϣ����ת���û��б�ҳ�棨User���޸�exist�ֶ�Ϊ0��
	public void deleteOneUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().deleteOneUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//ɾ��User���������û���Ϣ����ת���û��б�ҳ�棨User���޸�exist�ֶ�Ϊ0��
	public void deleteMultiUser() {
		String deleteStr = get("deleteStr");
		String deleteId[] = deleteStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = true;
				UserService service = new UserService();
				for(String aString : deleteId) {
					a = service.deleteOneUser(Integer.parseInt(aString));
				}
				if(a == true) {
					return true;
				}else {
					return false;
				}
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//�ָ�User���ڵ����û���Ϣ����ת���û��б�ҳ�棨User���޸�exist�ֶ�Ϊ1��
	public void recoveryOneUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().recoveryOneUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//�ָ�User���������û���Ϣ����ת���û��б�ҳ�棨User���޸�exist�ֶ�Ϊ1��
	public void recoveryMultiUser() {
		String recoveryStr = get("recoveryStr");
		String recoveryId[] = recoveryStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = true;
				UserService service = new UserService();
				for(String aString : recoveryId) {
					a = service.recoveryOneUser(Integer.parseInt(aString));
				}
				if(a == true) {
					return true;
				}else {
					return false;
				}
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//����ɾ��User�����û���Ϣ����ת���û��б�ҳ�棨User��delete��
	public void deleteThoroughUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().deleteThoroughUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
}
