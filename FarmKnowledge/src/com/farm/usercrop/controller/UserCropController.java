package com.farm.usercrop.controller;

import java.sql.SQLException;
import com.farm.userbag.service.BagService;
import com.farm.usercrop.service.UserCropService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

public class UserCropController extends Controller{
	//��ֲ����
	public void raiseCrop(int userId, int cropId, String landNumber) {
		Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a1 = new BagService().lessCropBag(userId, cropId);
				boolean a2 = new UserCropService().raiseNewCrop(userId, landNumber, cropId);
				if(a1 == true && a2 == true) { //��ӳɹ�
					return true;
				}else { //���ʧ�ܣ�����ص�
					return false;
				}
			}
		});
	}

}
