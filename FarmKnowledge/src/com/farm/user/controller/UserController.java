package com.farm.user.controller;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	//�û���¼
	public void loginByOpenId() {
		
		String json = get(0);
		
		JSONObject jsonObject = new JSONObject(json);
		String openId = jsonObject.getString("openId");
		String nickName = jsonObject.getString("nickName");
		String photo = URLDecoder.decode(jsonObject.getString("photo"));
		
		UserService service = new UserService();
		if(service.isExistUser(openId)) { //�Ѵ��ڸ��û�����ѯ�û���Ϣ
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //�����ڸ��û�������û�
			boolean addUser = service.addUser(openId, nickName, photo);
			if(addUser) { 				  //������û��ɹ�
				User user = service.findUserByOpenId(openId);
				renderJson(user);
			}else { 					  //������û�ʧ��
				renderText("������û�ʧ��");
			}
		}
	}
	
}
