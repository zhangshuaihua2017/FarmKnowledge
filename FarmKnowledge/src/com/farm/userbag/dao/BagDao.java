package com.farm.userbag.dao;

import java.util.List;

import com.farm.model.UserBag;

public class BagDao {
	
	//购买后添加种子到背包（UserBag）
	public void addCropToBag(int userId,int cropId,int num) {
		List<UserBag> list = UserBag.dao.find("select * from userbag where userId = ? and cropId = ?",userId,cropId);
		if(list.size()!=0) {
			UserBag bag = list.get(0);
			bag.set("number", bag.getInt("number")+num).update();
			return;
		}
		new UserBag().set("userId", userId).set("cropId", cropId).set("number", num).save(); 
	}
	
	//使用背包中种子
	public boolean lessCropInBag(int userId,int cropId) {
		List<UserBag> list = UserBag.dao.find("select * from userbag where userId = ? and cropId = ?",userId,cropId);
		if(list.size()!=0) {
			UserBag bag = list.get(0);
			if(bag.getInt("number")==1) {
				UserBag.dao.deleteById(bag.getInt("id"));
			}else {
				bag.set("number", bag.getInt("number")-1).update();
			}
			return true;
		}
		return false;
	}
}
