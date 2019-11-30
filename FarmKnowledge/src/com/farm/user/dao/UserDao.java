package  com.farm.user.dao;

import java.util.List;

import com.farm.model.User;
import com.farm.model.UserAuthority;
import com.jfinal.plugin.activerecord.Db;

public class UserDao {
	
	//����openId��ѯUser�����û���Ϣ
	public User findUserByOpenId(String openId){
		List<User> list = User.dao.find("select user.* from user,userAuthority where user.userId=userAuthority.userId and userAuthority.openId=?",openId);
		if(list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	//User����������ͷ��nickName��photo��
	public boolean addUser(String nickName, String photo){
		boolean succeed =  new User().set("nickName", nickName).set("photo", photo).set("level", 1).set("experience", 0).set("money", 0).set("online", 1).save();
		return succeed;
	}
	//UserAuthority���ڲ���userId��openId��token
	public boolean addUserAuthority(int userId, String openId){
		boolean succeed =  new UserAuthority().set("userId", userId).set("openId", openId).set("type", "QQ").save();
		return succeed;
	}		
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û�
	public boolean isExistUser(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=?",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//User�������һ�����ݵ�userId
	public int getLastUserId(){
		int id =  Db.queryInt("select userId from user order by userId desc limit 1");
		return id;
	}
	
}
