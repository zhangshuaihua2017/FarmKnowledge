package com.farm.user.controller;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.farm.usercrop.service.UserCropService;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;

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
	
	//增加浇水.施肥次数（userId，数量）
	public void addUserWater(int id,int water,int fertilizer) {
		new UserService().addWaterAndFer(id, water, fertilizer);
	}
	//减少浇水次数（userId）
	public void lessUserWater(int id) {
		new UserService().lessW(id);
	}
	//减少施肥次数（userId）
	public void lessUserFertilizer(int id) {
		new UserService().lessF(id);
	}
	
	//浇水（userId,landNumber ）
	public void waterC(int userId,String landNumber) {
		new UserCropService().waterCr(userId, landNumber);
	}
	//施肥
	public void fertilizeC(int userId,String landNumber) {
		new UserCropService().fertilizerCr(userId, landNumber);
	}
	
	//收获
	public void harvest(int userId, String landNumber) {
		new UserCropService().getC(userId, landNumber);
	}
}
