package com.farm.userbag.service;

import com.farm.userbag.dao.BagDao;

public class BagService {
	//购买后添加种子到背包（UserBag）
	public void addToBag(int userId,int cropId,int num) {
		new BagDao().addCropToBag(userId, cropId, num);
	}
	
	//使用背包中种子
	public void lessCropBag(int userId,int cropId) {
		new BagDao().lessCropInBag(userId, cropId);
	}
}
