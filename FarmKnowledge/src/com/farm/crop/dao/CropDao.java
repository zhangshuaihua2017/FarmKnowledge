package com.farm.crop.dao;

import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropDao {
	
	//��ѯCrop��������������Ϣ��Crop��
	public Page<Crop> findCropPage(int currentPage,int everyCount,String name,int exist) {
		Page<Crop> cropPage;
		if(name == null || name.equals("")) {
			cropPage = Crop.dao.paginate(currentPage, everyCount, "select *","from crop where exist=?",exist);	
		}else {
			cropPage = Crop.dao.paginate(currentPage, everyCount, "select *","from crop where exist=? and name like?",exist,"%"+name+"%");
		}
		return cropPage;
	}
	
}
