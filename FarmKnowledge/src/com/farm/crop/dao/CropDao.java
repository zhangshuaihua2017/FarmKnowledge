package com.farm.crop.dao;

import java.util.ArrayList;
import java.util.List;

import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropDao {
	
	//查询Crop表内所有作物信息（Crop表）
	public Page<Crop> findCropPage(int pageNumber,int everyCount,String name,int exist) {
		Page<Crop> cropPage;
		if(name == null || name.equals("")) {
			cropPage = Crop.dao.paginate(pageNumber, everyCount, "select *","from crop where exist=?",exist);	
		}else {
			cropPage = Crop.dao.paginate(pageNumber, everyCount, "select *","from crop where exist=? and name like?",exist,"%"+name+"%");
		}
		return cropPage;
	}
	//查询某作物经验，金币 , 参数（cropId）
	public int[] findCropExAndMoney(int id) {
		List<Crop> list = Crop.dao.find("select * from crop where id = ?",id);
		if(list.size()!=0) {
			Crop crop = list.get(0);
			int[] arr = {crop.getInt("experience"),crop.getInt("value")};
			return arr;
		}
		return null;
	}
	
}
