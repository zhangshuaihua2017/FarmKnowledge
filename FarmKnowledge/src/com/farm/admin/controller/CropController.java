package com.farm.admin.controller;

import com.farm.crop.service.CropService;
import com.farm.model.Crop;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class CropController extends Controller{
	
	//查询Crop表内所有作物信息（Crop表），跳转到作物列表页面，
	public void findCropPage() {
		String name = get("name");
		String page = get("currentPage");
		String count = get("pageSize");
		int exist = getInt("exist");
		int currentPage;
		int everyCount;
		
		if(page == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(page);
		}
		if(count == null) {
			everyCount = 1;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<Crop> list = new CropService().findCropPage(currentPage,everyCount,name,exist);
		setAttr("cropPage", list);
		if(exist == 1) {
			renderJsp("/crop-list.jsp");
		}else {
			renderJsp("/crop-del.jsp");
		}
	}
	
}
