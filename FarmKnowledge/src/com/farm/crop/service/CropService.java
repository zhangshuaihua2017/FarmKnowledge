package com.farm.crop.service;

import java.util.List;

import com.farm.crop.dao.CropDao;
import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropService {
	
	//��ѯCrop��������������Ϣ��Crop��
	public Page<Crop> findCropPage(int pageNumber,int everyCount,String name,int exist) {
		return new CropDao().findCropPage(pageNumber,everyCount,name,exist);
	}
	
	//��ѯCrop���飬��ң�Crop��
	public int[] findEandM(int id) {
		return new CropDao().findCropExAndMoney(id);
	}
}
