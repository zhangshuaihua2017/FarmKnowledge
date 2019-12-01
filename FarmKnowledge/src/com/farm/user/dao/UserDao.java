package  com.farm.user.dao;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.farm.model.User;
import com.farm.model.UserAuthority;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class UserDao {
	
	//根据openId查询User表内用户信息
	public User findUserByOpenId(String openId){
		List<User> list = User.dao.find("select user.* from user,userAuthority where user.userId=userAuthority.userId and userAuthority.openId=?",openId);
		if(list.size() != 0) {
			User user = list.get(0);
			user.set("photo", URLEncoder.encode(user.getStr("photo")));
			return list.get(0);
		}
		return null;
	}
	
	//User表插入别名、头像（nickName、photo）
	public boolean addUser(String nickName, String photo){
		boolean succeed =  new User().set("nickName", nickName).set("photo", photo).set("level", 1).set("experience", 0).set("money", 0).set("online", 1).save();
		return succeed;
	}
	//UserAuthority表内插入userId、openId、token
	public boolean addUserAuthority(int userId, String openId){
		boolean succeed =  new UserAuthority().set("userId", userId).set("openId", openId).set("type", "QQ").save();
		return succeed;
	}		
	
	//根据openId判断UserAuthority表内是否存在该用户
	public boolean isExistUser(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=?",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//User表获得最后一条数据的userId
	public int getLastUserId(){
		int id =  Db.queryInt("select userId from user order by userId desc limit 1");
		return id;
	}
	
	//查询User表内用户信息（User表）
	public Page<User> findUserPage(int currentPage,int everyCount,String accout,int exist) {
		Page<User> userPage;
		if(accout == null || accout.equals("")) {
			userPage = User.dao.paginate(currentPage, everyCount, "select *","from user where exist=?",exist);	
		}else {
			userPage = User.dao.paginate(currentPage, everyCount, "select *","from user where exist=? and accout like?",exist,"%"+accout+"%");
		}
		return userPage;
	}
	
}
