package user.controller;

import com.jfinal.core.Controller;

import model.User;
import user.service.UserService;

public class UserController extends Controller{
	
	//�û���¼
	public void loginUser() {
		String openId = get(0);
		String nickName = get(1);
		String photo = get(2);
		
		UserService service = new UserService();
		if(service.isExistUser(openId)) { //�Ѵ��ڸ��û�����ѯ�û���Ϣ
			User user = service.findUserByOpenId(openId);
			renderJson(user);
		}else { 						  //�����ڸ��û�������û�
			boolean addUser = service.addUser(openId, nickName, photo);
			if(addUser) { 				  //������û��ɹ�
				renderText("������û��ɹ�");
			}else { 					  //������û�ʧ��
				renderText("������û�ʧ��");
			}
		}
	}

}
