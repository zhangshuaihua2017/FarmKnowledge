package com.farm.user.controller;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.farm.usercrop.service.UserCropService;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;

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
	
	//���ӽ�ˮ.ʩ�ʴ�����userId��������
	public void addUserWater(int id,int water,int fertilizer) {
		new UserService().addWaterAndFer(id, water, fertilizer);
	}
	//���ٽ�ˮ������userId��
	public void lessUserWater(int id) {
		new UserService().lessW(id);
	}
	//����ʩ�ʴ�����userId��
	public void lessUserFertilizer(int id) {
		new UserService().lessF(id);
	}
	
	//��ˮ��userId,landNumber ��
	public void waterC(int userId,String landNumber) {
		new UserCropService().waterCr(userId, landNumber);
	}
	//ʩ��
	public void fertilizeC(int userId,String landNumber) {
		new UserCropService().fertilizerCr(userId, landNumber);
	}
	
	//�ջ�
	public void harvest(int userId, String landNumber) {
		new UserCropService().getC(userId, landNumber);
	}
}
