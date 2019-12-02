package com.farm.user.controller;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;

public class UserController extends Controller{
	
	//用户登录
	public void loginByOpenId() {
<<<<<<< Updated upstream
		String openId = get(0);
		String nickName = get(1);
		String photo = get(2);
=======
		
		String jsonStr =  HttpKit.readData(getRequest());
		

		System.out.println(jsonStr);
		
		JSONObject jsonObject = new JSONObject(jsonStr);
		String openId = jsonObject.getString("openId");
		String nickName = jsonObject.getString("nickName");
		String photo = URLDecoder.decode(jsonObject.getString("photo"));
>>>>>>> Stashed changes
		
		System.out.println(jsonObject.toString());
		UserService service = new UserService();
		if(service.isExistUser(openId)) { //已存在该用户，查询用户信息
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //不存在该用户，添加用户
			boolean addUser = service.addUser(openId, nickName, photo);
			if(addUser) { 				  //添加新用户成功
				User user = service.findUserByOpenId(openId);
				renderJson(user);
			}else { 					  //添加新用户失败
				renderText("添加新用户失败");
			}
		}
	}
	
	//查询所有用户列表
	public void findAllUser() {
		
	}
	
}
