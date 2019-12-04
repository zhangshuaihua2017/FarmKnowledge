package com.farm.crop.service;

import java.util.List;

import com.farm.crop.dao.CropDao;
import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropService {
	
	//查询Crop表内所有作物信息（Crop表）
	public Page<Crop> findCropPage(int pageNumber,int everyCount,String name,int exist) {
		return new CropDao().findCropPage(pageNumber,everyCount,name,exist);
	}
	
	//查询Crop表经验，金币（Crop表）
	public int[] findEandM(int id) {
		return new CropDao().findCropExAndMoney(id);
	}
}
