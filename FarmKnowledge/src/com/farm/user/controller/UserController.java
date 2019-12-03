package com.farm.user.controller;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.template.expr.ast.Id;

public class UserController extends Controller{
	
	//用户登录
	public void loginByOpenId() {
		String jsonStr =  HttpKit.readData(getRequest());
		System.out.println(jsonStr);
		JSONObject jsonObject = new JSONObject(jsonStr);
		String openId = jsonObject.getString("openId");
		String nickName = jsonObject.getString("nickName");
		String photo = URLDecoder.decode(jsonObject.getString("photo"));
		System.out.println(jsonObject.toString());
		UserService service = new UserService();
		if(service.isExistUserByOpenId(openId)) { //已存在该用户，查询用户信息
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //不存在该用户，添加用户
			boolean addUser = service.addUser(openId, nickName, photo, "QQ");
			if(addUser) { 				  //添加新用户成功
				User user = service.findUserByOpenId(openId);
				renderJson(user);
			}else { 					  //添加新用户失败
				renderText("添加新用户失败");
			}
		}
	}
	
	//根据openId查询用户信息
	public void findByOpenId() {
		String jsonStr =  HttpKit.readData(getRequest());
		JSONObject jsonObject = new JSONObject(jsonStr);
		String openId = jsonObject.getString("openId");
		
		User user = new UserService().findUserByOpenId(openId);
		renderJson(user);
	}
}
