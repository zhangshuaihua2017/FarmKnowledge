package com.farm.crop.service;

import com.farm.crop.dao.CropDao;
import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropService {
	
	//��ѯCrop��������������Ϣ��Crop��
	public Page<Crop> findCropPage(int currentPage,int everyCount,String name,int exist) {
		return new CropDao().findCropPage(currentPage,everyCount,name,exist);
	}
}
