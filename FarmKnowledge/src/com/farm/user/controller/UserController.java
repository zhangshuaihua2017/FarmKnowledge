package com.farm.user.controller;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	//�û���¼
	public void loginByOpenId() {
		String openId = get(0);
		String nickName = get(1);
		String photo = get(2);
		
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
	
	//��ѯ�����û��б�
	public void findAllUser() {
		
	}
	
}
