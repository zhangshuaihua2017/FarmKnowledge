package user.controller;

import com.jfinal.core.Controller;

import model.User;
import user.service.UserService;

public class UserController extends Controller{
	
	//用户登录
	public void loginUser() {
		String openId = get(0);
		String nickName = get(1);
		String photo = get(2);
		
		UserService service = new UserService();
		if(service.isExistUser(openId)) { //已存在该用户，查询用户信息
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //不存在该用户，添加用户
			boolean addUser = service.addUser(openId, nickName, photo);
			if(addUser) { 				  //添加新用户成功
				renderText("添加新用户成功");
			}else { 					  //添加新用户失败
				renderText("添加新用户失败");
			}
		}
	}

}
