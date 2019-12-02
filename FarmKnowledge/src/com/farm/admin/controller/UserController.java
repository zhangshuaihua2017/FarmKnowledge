package com.farm.admin.controller;

import java.sql.SQLException;

import com.farm.model.User;
import com.farm.user.dao.UserDao;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller{
	
	//查询User表内用户信息，跳转到用户列表页面（User表）
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

	//删除User表内单个用户信息，跳转到用户列表页面（User表修改exist字段为0）
	public void deleteOneUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().deleteOneUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//删除User表内批量用户信息，跳转到用户列表页面（User表修改exist字段为0）
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
	
	//恢复User表内单个用户信息，跳转到用户列表页面（User表修改exist字段为1）
	public void recoveryOneUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().recoveryOneUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//恢复User表内批量用户信息，跳转到用户列表页面（User表修改exist字段为1）
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
	
	//彻底删除User表内用户信息，跳转到用户列表页面（User表delete）
	public void deleteThoroughUser() {
		int userId = getInt("userId");
		boolean succeed = new UserService().deleteThoroughUser(userId);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//添加用户信息（授权id、账号、别名、头像、登陆类型）
	public void addUser() {
		String openId = get("openId");
		String nickName = get("nickName");
		String photo = get("photo");
		String type = get("type");
		
		UserService service = new UserService();
		if(!service.isExistUserByOpenId(openId)) { //不存在该用户，可以添加
			boolean succeed = service.addUser(openId, nickName, photo, type);
			if(succeed == true) {
				renderText("succeed");
			}else {
				renderText("fail");
			}
		}else { //已存在该用户，不可添加
			renderText("already");
		}
		
	}

	//根据用户id获取到要修改的用户信息（账号、别名、头像）
	public void getUpdateUserInfo() {
		int id = getInt("id");
		User user = new UserService().getUpdateUserInfo(id);
		setSessionAttr("user", user);
		renderText("succeed");
	}
	
	//修改用户信息（账号、别名、头像）
	public void updateUser() {
		int id = getInt("id");
		String accout = get("accout");
		String nickName = get("nickName");
		String photo = get("photo");
		
		UserService service = new UserService();
		if(!service.isExistUserByAccout(accout)) { //不存在该账号，可以修改
			boolean succeed = service.updateUser(id, accout, nickName, photo);
			if(succeed == true) {
				renderText("succeed");
			}else {
				renderText("fail");
			}
		}else { //已存在该账号，不可修改
			renderText("already");
		}
			
	}
}
