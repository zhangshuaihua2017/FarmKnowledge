package com.farm.crop.service;

import com.farm.crop.dao.CropDao;
import com.farm.model.Crop;
import com.jfinal.plugin.activerecord.Page;

public class CropService {
	
	//��ѯCrop��������������Ϣ��Crop��
	public Page<Crop> findCropPage(int pageNumber,int everyCount,String name,int exist) {
		return new CropDao().findCropPage(pageNumber,everyCount,name,exist);
	}
	
	//ɾ��Crop���ڵ���������Ϣ��Crop���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneCrop(int id) {
		return new CropDao().deleteOneCrop(id);
	}
	
	//�ָ�Crop���ڵ���������Ϣ��Crop���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneCrop(int id) {
		return new CropDao().recoveryOneCrop(id);
	}
	
	//����ɾ��Crop����������Ϣ��Crop��delete��
	public boolean deleteThoroughCrop(int id) {
		return new CropDao().deleteThoroughCrop(id);
	}
	
	//���������Ϣ
	public boolean addCrop(String name, int price, String img1, String img2, String img3, int matureTime, int value, int experience) {
		return new CropDao().addCrop(name, price, img1, img2, img3, matureTime, value, experience);
	}
	
	//��������id��ȡ��Ҫ�޸ĵ�������Ϣ
	public Crop getUpdateCropInfo(int id) {
		return new CropDao().getUpdateCropInfo(id);
	}
	
	//�޸�������Ϣ
	public boolean updateCrop(int id, String name, int price, String img1, String img2, String img3, int matureTime, int value, int experience) {
		return new CropDao().updateCrop(id, name, price, img1, img2, img3, matureTime, value, experience);
	}
}
