package com.farm.userbag.service;

import com.farm.userbag.dao.BagDao;

public class BagService {
	//������������ӵ�������UserBag��
	public void addToBag(int userId,int cropId,int num) {
		new BagDao().addCropToBag(userId, cropId, num);
	}
	
	//ʹ�ñ���������
	public boolean lessCropBag(int userId,int cropId) {
		return new BagDao().lessCropInBag(userId, cropId);
	}
}