package com.farm.usercrop.service;

import com.farm.user.service.UserService;
import com.farm.usercrop.dao.UserCropDao;

public class UserCropService {
	//��ˮ(����:usercropId��userId)
	public void waterCr(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().waterCrop(userId,ucId);
	}
	
	//ʩ��(����:usercropId,userId)
	public void fertilizerCr(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().fertilizerCrop(userId,ucId);
	}
	
	//�ջ�User��crop��usercrop��
	public void getC(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().getCrop(userId, ucId);
	}
	
	//��ֲ������,,,(û�п��ǵ�ǰ�����Ƿ���������ռ�ã���ǰ�˿���QAQ��
	public boolean raiseNewCrop(int userId,String landNumber,int cropId) {
		return new UserCropDao().addNewCrop(userId, landNumber, cropId);
	}
}
