package com.farm.crop.service;

import com.farm.crop.dao.CropDao;
import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropService {
	
	//查询Crop表内所有作物信息（Crop表）
	public Page<Crop> findCropPage(int pageNumber,int everyCount,String name,int exist) {
		return new CropDao().findCropPage(pageNumber,everyCount,name,exist);
	}
	
	//删除Crop表内单个作物信息（Crop表修改exist字段为0）
	public boolean deleteOneCrop(int id) {
		return new CropDao().deleteOneCrop(id);
	}
	
	//恢复Crop表内单个作物信息（Crop表修改exist字段为1）
	public boolean recoveryOneCrop(int id) {
		return new CropDao().recoveryOneCrop(id);
	}
	
	//彻底删除Crop表内作物信息（Crop表delete）
	public boolean deleteThoroughCrop(int id) {
		return new CropDao().deleteThoroughCrop(id);
	}
	
	//添加作物信息
	public boolean addCrop(String name, int price, String img1, String img2, String img3, int matureTime, int value, int experience) {
		return new CropDao().addCrop(name, price, img1, img2, img3, matureTime, value, experience);
	}
	
	//根据作物id获取到要修改的作物信息
	public Crop getUpdateCropInfo(int id) {
		return new CropDao().getUpdateCropInfo(id);
	}
	
	//修改作物信息
	public boolean updateCrop(int id, String name, int price, String img1, String img2, String img3, int matureTime, int value, int experience) {
		return new CropDao().updateCrop(id, name, price, img1, img2, img3, matureTime, value, experience);
	}
}
