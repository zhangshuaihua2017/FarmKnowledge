package com.farm.usercrop.dao;

import java.util.List;

import com.farm.crop.service.CropService;
import com.farm.model.User;
import com.farm.model.UserCrop;
import com.farm.user.service.UserService;

public class UserCropDao {
	//��ˮ(����:usercropId,userId)
	public void waterCrop(int userId,int ucId) {
		List<UserCrop> list = UserCrop.dao.find("select * from usercrop where id = ?",ucId);
		if(list.size()!=0) {
			UserCrop uc = list.get(0);
			if(uc.getInt("progress")>=100) {
				return;
			}else if(uc.getInt("progress")<=95) {
				uc.set("progress", uc.getInt("progress")+5).update();
			}else {
				uc.set("progress",100).update();
			}
		}
		new UserService().lessW(userId);
	}
	
	//ʩ��(����:usercropId,userId)
	public void fertilizerCrop(int userId,int ucId) {
		List<UserCrop> list = UserCrop.dao.find("select * from usercrop where id = ?",ucId);
		if(list.size()!=0) {
			UserCrop uc = list.get(0);
			if(uc.getInt("progress")>=100) {
				return;
			}else if(uc.getInt("progress")<=90) {
				uc.set("progress", uc.getInt("progress")+10).update();
			}else {
				uc.set("progress", 100).update();
			}
		}
		new UserService().lessF(userId);
	}
	
	//�ջ񣨲�����usercropId��userId��
	public void getCrop(int userId,int ucId) {
		List<UserCrop> list = UserCrop.dao.find("select * from usercrop where id = ?",ucId);
		if(list.size()!=0) {
			UserCrop uc = list.get(0);
			if(uc.getInt("progress")==100) {
				if(uc.getInt("status")==0) {
					int[] arr = new CropService().findEandM(uc.getInt("cropId"));
					new UserService().addEandM(userId, arr[0],arr[1]);
					uc.set("status", 1).update();
				}
			}
		}
	}
	//��ֲ������,,,(û�п��ǵ�ǰ�����Ƿ���������ռ�ã���ǰ�˿���QAQ��
	public boolean addNewCrop(int userId,String landNumber,int cropId) {
		UserCrop uc = new UserCrop();
		uc.set("cropId", cropId);
		uc.set("status", 0);
		uc.set("progress", 0);
		uc.save();
		User.dao.findById(userId).set(landNumber, uc.getStr("id")).update();
		return true;
	}
}
