package admin.controller;

import com.jfinal.core.Controller;

import admin.service.AdminService;
import model.Admin;

public class AdminController extends Controller{

	//跳转到登录页面
	public void index() {
		renderJsp("/login.jsp");
	}
	
	//管理员登陆
	public void login() {
		String uName = get("uName");
		String password = get("password");
		AdminService service = new AdminService();
		boolean isExist = service.isExistAdmin(uName);
		if(isExist) {
			Admin admin = service.loginAdmin(uName, password);
			if(admin != null) {
				setSessionAttr("admin",admin);
				forwardAction("/admin/gotoIndex");
			}else {
				renderText("fail");
			}
		}else {
			renderText("notExist");
		}
	}
	
	//跳转到主页面
	public void gotoIndex() {
		renderJsp("/index.jsp");
	}
}
