package com.farm.usercrop.service;

import com.farm.user.service.UserService;
import com.farm.usercrop.dao.UserCropDao;

public class UserCropService {
	//浇水(参数:usercropId，userId)
	public void waterCr(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().waterCrop(userId,ucId);
	}
	
	//施肥(参数:usercropId,userId)
	public void fertilizerCr(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().fertilizerCrop(userId,ucId);
	}
	
	//收获（User表，crop表，usercrop表）
	public void getC(int userId,String landNumber) {
		int ucId = new UserService().findUcId(userId, landNumber);
		new UserCropDao().getCrop(userId, ucId);
	}
	
	//种植新作物,,,(没有考虑当前土地是否已有作物占用，由前端考虑QAQ）
	public boolean raiseNewCrop(int userId,String landNumber,int cropId) {
		return new UserCropDao().addNewCrop(userId, landNumber, cropId);
	}
}
