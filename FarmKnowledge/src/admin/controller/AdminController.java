package admin.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;

import admin.service.AdminService;
import model.Admin;

public class AdminController extends Controller{

	public void index() {
		renderJsp("/login.jsp");
	}
	
	//����Ա��½
	public void login() {
		String uName = get("uName");
		String password = get("password");
		AdminService service = new AdminService();
		boolean isExist = service.isExistAdmin(uName);
		if(isExist) {
			Admin admin = service.loginAdmin(uName, password);
			if(admin != null) {
				setSessionAttr("admin",admin);
				setSessionAttr("test", "test");
				forwardAction("/admin/gotoIndex");
			}else {
				renderText("fail");
			}
		}else {
			renderText("notExist");
		}
	}
	
	//��ת����ҳ��
	public void gotoIndex() {
		renderJsp("/index.jsp");
	}
}
