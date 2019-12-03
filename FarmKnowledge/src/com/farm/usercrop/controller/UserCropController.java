package com.farm.usercrop.controller;

import com.farm.userbag.service.BagService;
import com.farm.usercrop.service.UserCropService;

public class UserCropController {
	//ÖÖÖ²×÷Îï
	public void raiseCrop(int userId, int cropId, String landNumber) {
		new BagService().lessCropBag(userId, cropId);
		new UserCropService().raiseNewCrop(userId, landNumber, cropId);
	}

}
