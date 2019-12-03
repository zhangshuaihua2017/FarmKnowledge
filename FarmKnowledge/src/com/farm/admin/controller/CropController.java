package com.farm.admin.controller;

import java.sql.SQLException;

import com.farm.crop.service.CropService;
import com.farm.model.Crop;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class CropController extends Controller{
	
	//��ѯCrop��������������Ϣ����ת�������б�ҳ�棨Crop��
	public void findCropPage() {
		String name = get("name");
		String page = get("pageNumber");
		String count = get("pageSize");
		int exist = getInt("exist");
		int pageNumber;
		int everyCount;
		
		if(page == null) {
			pageNumber = 1;
		}else {
			pageNumber = Integer.parseInt(page);
		}
		if(count == null) {
			everyCount = 1;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<Crop> list = new CropService().findCropPage(pageNumber,everyCount,name,exist);
		setAttr("cropPage", list);
		if(exist == 1) {
			renderJsp("/crop-list.jsp");
		}else {
			renderJsp("/crop-del.jsp");
		}
	}
	
	//ɾ��Crop���ڵ���������Ϣ����ת�������б�ҳ�棨Crop���޸�exist�ֶ�Ϊ0��
	public void deleteOneCrop() {
		int id = getInt("id");
		boolean succeed = new CropService().deleteOneCrop(id);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//ɾ��Crop��������������Ϣ����ת�������б�ҳ�棨Crop���޸�exist�ֶ�Ϊ0��
	public void deleteMultiCrop() {
		String deleteStr = get("deleteStr");
		String deleteId[] = deleteStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = true;
				CropService service = new CropService();
				for(String aString : deleteId) {
					a = service.deleteOneCrop(Integer.parseInt(aString));
				}
				if(a == true) {
					return true;
				}else {
					return false;
				}
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}

	//�ָ�Crop���ڵ���������Ϣ����ת�������б�ҳ�棨Crop���޸�exist�ֶ�Ϊ1��
	public void recoveryOneCrop() {
		int id = getInt("id");
		boolean succeed = new CropService().recoveryOneCrop(id);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//�ָ�Crop��������������Ϣ����ת�������б�ҳ�棨Crop���޸�exist�ֶ�Ϊ1��
	public void recoveryMultiCrop() {
		String recoveryStr = get("recoveryStr");
		String recoveryId[] = recoveryStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = true;
				CropService service = new CropService();
				for(String aString : recoveryId) {
					a = service.recoveryOneCrop(Integer.parseInt(aString));
				}
				if(a == true) {
					return true;
				}else {
					return false;
				}
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//����ɾ��Crop����������Ϣ����ת�������б�ҳ�棨Crop��delete��
	public void deleteThoroughCrop() {
		int id = getInt("id");
		boolean succeed = new CropService().deleteThoroughCrop(id);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//���������Ϣ
	public void addCrop() {
		String name = get("name");
		int price = getInt("price");
		String img1 = get("img1");
		String img2 = get("img2");
		String img3 = get("img3");
		int matureTime = getInt("matureTime");
		int value = getInt("value");
		int experience = getInt("experience");
		
		CropService service = new CropService();
		boolean succeed = service.addCrop(name, price, img1, img2, img3, matureTime, value, experience);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//��������id��ȡ��Ҫ�޸ĵ�������Ϣ
	public void getUpdateCropInfo() {
		int id = getInt("id");
		Crop crop = new CropService().getUpdateCropInfo(id);
		if(crop != null) {
			setSessionAttr("crop", crop);
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//�޸�������Ϣ
	public void updateCrop() {
		int id = getInt("id");
		String name = get("name");
		int price = getInt("price");
		String img1 = get("img1");
		String img2 = get("img2");
		String img3 = get("img3");
		int matureTime = getInt("matureTime");
		int value = getInt("value");
		int experience = getInt("experience");
		
		CropService service = new CropService();
		boolean succeed = service.updateCrop(id, name, price, img1, img2, img3, matureTime, value, experience);
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}	
	}
	
}
