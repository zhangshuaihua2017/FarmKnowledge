package com.farm.userbag.controller;

import com.farm.userbag.service.BagService;

public class BagController {
	
	//购买种子后添加到背包
	public void buyCrop(int userId, int cropId, int num) {
		new BagService().addToBag(userId, cropId, num);
	}
}
