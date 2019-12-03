package com.farm.admin.controller;

import java.sql.SQLException;

import com.farm.model.User;
import com.farm.user.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller{
	
	//��ѯUser�����û���Ϣ����ת���û��б�ҳ�棨User��
	public void findUserPage() {
		String accout = get("accout");
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
			everyCount = 4;
		}else {
			everyCount = Integer.parseInt(count);
		}
		
		Page<User> list = new UserService().findUserPage(pageNumber,everyCount,accout,exist);
		setAttr("userPage", list);
		if(exist == 1) {
			renderJsp("/member-list.jsp");
		}else {
			renderJsp("/member-del.jsp");
		}
		
	}

	//ɾ��User���ڵ����û���Ϣ����ת���û��б�ҳ�棨User��UserAuthority���޸�exist�ֶ�Ϊ0��
	public void deleteOneUser() {
		int userId = getInt("userId");
		
		UserService service = new UserService();
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a1 = service.deleteOneUser(userId);
				boolean a2 = service.deleteOneUserAuthority(userId);
				if(a1 == true && a2 == true) {
					return true;
				}
				return false;
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//ɾ��User���������û���Ϣ����ת���û��б�ҳ�棨User��UserAuthority���޸�exist�ֶ�Ϊ0��
	public void deleteMultiUser() {
		String deleteStr = get("deleteStr");
		String deleteId[] = deleteStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = false;
				boolean b = false;
				UserService service = new UserService();
				for(String aString : deleteId) {
					a = service.deleteOneUser(Integer.parseInt(aString));
					b = service.deleteOneUserAuthority(Integer.parseInt(aString));
				}
				if(a == true && b == true) {
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
	
	//�ָ�User���ڵ����û���Ϣ����ת���û��б�ҳ�棨User��UserAuthority���޸�exist�ֶ�Ϊ1��
	public void recoveryOneUser() {
		int userId = getInt("userId");
		
		UserService service = new UserService();
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a1 = service.recoveryOneUser(userId);
				boolean a2 = service.recoveryOneUserAuthority(userId);
				if(a1 == true && a2 == true) {
					return true;
				}
				return false;
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//�ָ�User���������û���Ϣ����ת���û��б�ҳ�棨User��UserAuthority���޸�exist�ֶ�Ϊ1��
	public void recoveryMultiUser() {
		String recoveryStr = get("recoveryStr");
		String recoveryId[] = recoveryStr.split(",");
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a = false;
				boolean b = false;
				UserService service = new UserService();
				for(String aString : recoveryId) {
					a = service.recoveryOneUser(Integer.parseInt(aString));
					b = service.recoveryOneUserAuthority(Integer.parseInt(aString));
				}
				if(a == true && b == true) {
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
	
	//����ɾ��User�����û���Ϣ����ת���û��б�ҳ�棨User��UserAuthority��delete��
	public void deleteThoroughUser() {
		int userId = getInt("userId");
		
		UserService service = new UserService();
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean a1 = service.deleteThoroughUser(userId);
				boolean a2 = service.deleteThoroughUserAuthority(userId);
				if(a1 == true && a2 == true) {
					return true;
				}
				return false;
			}
		});
		if(succeed == true) {
			renderText("succeed");
		}else {
			renderText("fail");
		}
	}
	
	//����û���Ϣ����Ȩid���˺š�������ͷ�񡢵�½���ͣ�
	public void addUser() {
		String openId = get("openId");
		String nickName = get("nickName");
		String photo = get("photo");
		String type = get("type");
		
		UserService service = new UserService();
		if(!service.isExistUserByOpenIdAll(openId)) { //�����ڸ��û����������
			boolean succeed = service.addUser(openId, nickName, photo, type);
			if(succeed == true) {
				renderText("succeed");
			}else {
				renderText("fail");
			}
		}else { //�Ѵ��ڸ��û����������
			renderText("already");
		}		
	}

	//�����û�id��ȡ��Ҫ�޸ĵ��û���Ϣ���˺š�������ͷ��
	public void getUpdateUserInfo() {
		int id = getInt("id");
		User user = new UserService().getUpdateUserInfo(id);
		if(user != null) {
			setSessionAttr("user", user);
			renderText("succeed");
		}else {
			renderText("fail");
		}			
	}
	
	//�޸��û���Ϣ���˺š�������ͷ�񣩣������޸�ǰ�˺�������
	public void updateUser() {
		String oldAccout = get("oldAccout");
		String newAccout = get("newAccout");
		String nickName = get("nickName");
		String photo = get("photo");
		
		UserService service = new UserService();
		if(!service.isExistUserByAccout(oldAccout,newAccout)) {
			boolean succeed = service.updateUser(oldAccout, newAccout, nickName, photo);
			if(succeed == true) {
				renderText("succeed");
			}else {
				renderText("fail");
			}
		}else { //�Ѵ��ڸ��˺ţ������޸�
			renderText("already");
		}	
	}
	
}
