package com.farm.userbag.controller;

import com.farm.userbag.service.BagService;
import com.jfinal.core.Controller;

public class BagController extends Controller{
	
	//购买种子后添加到背包
	public void buyCrop() {
		int userId = Integer.parseInt(getPara(0));
		int cropId = Integer.parseInt(getPara(1));
		int num = Integer.parseInt(getPara(2));
		new BagService().addToBag(userId, cropId, num);
	}
}
