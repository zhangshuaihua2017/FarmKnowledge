package com.farm.userbag.controller;

import com.farm.userbag.service.BagService;

public class BagController {
	
	//�������Ӻ���ӵ�����
	public void buyCrop(int userId, int cropId, int num) {
		new BagService().addToBag(userId, cropId, num);
	}
}
