package com.farm.user.controller;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.template.expr.ast.Id;

public class UserController extends Controller{
	
	//�û���¼
	public void loginByOpenId() {
		String jsonStr =  HttpKit.readData(getRequest());
		System.out.println(jsonStr);
		JSONObject jsonObject = new JSONObject(jsonStr);
		String openId = jsonObject.getString("openId");
		String nickName = jsonObject.getString("nickName");
		String photo = URLDecoder.decode(jsonObject.getString("photo"));
		System.out.println(jsonObject.toString());
		UserService service = new UserService();
		if(service.isExistUserByOpenId(openId)) { //�Ѵ��ڸ��û�����ѯ�û���Ϣ
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //�����ڸ��û�������û�
			boolean addUser = service.addUser(openId, nickName, photo, "QQ");
			if(addUser) { 				  //������û��ɹ�
				User user = service.findUserByOpenId(openId);
				renderJson(user);
			}else { 					  //������û�ʧ��
				renderText("������û�ʧ��");
			}
		}
	}
	
	//����openId��ѯ�û���Ϣ
	public void findByOpenId() {
		String jsonStr =  HttpKit.readData(getRequest());
		JSONObject jsonObject = new JSONObject(jsonStr);
		String openId = jsonObject.getString("openId");
		
		User user = new UserService().findUserByOpenId(openId);
		renderJson(user);
	}
}
