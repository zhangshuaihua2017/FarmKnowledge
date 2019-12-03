package  com.farm.user.dao;

import java.net.URLEncoder;
import java.util.List;

import com.farm.model.User;
import com.farm.model.UserAuthority;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class UserDao {
	
	//根据openId查询User表内用户信息
	public User findUserByOpenId(String openId){
		List<User> list = User.dao.find("select user.* from user,userAuthority where user.id=userAuthority.userId and userAuthority.openId=?",openId);
		if(list.size() != 0) {
			User user = list.get(0);
			user.set("photo", URLEncoder.encode(user.getStr("photo")));
			return list.get(0);
		}
		return null;
	}
	
	//User表插入账号、别名、头像（accout、nickName、photo）
	public boolean addUser(String accout, String nickName, String photo){
		boolean succeed =  new User().set("accout", accout).set("nickName", nickName).set("photo", photo).save();
		return succeed;
	}
	//UserAuthority表内插入userId、openId、type
	public boolean addUserAuthority(int userId, String openId, String type){
		boolean succeed =  new UserAuthority().set("userId", userId).set("openId", openId).set("type", type).save();
		return succeed;
	}		
	
	//根据openId判断UserAuthority表内是否存在该用户exist=1
	public boolean isExistUserByOpenId(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=? and exist=1",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//根据openId判断UserAuthority表内是否存在该用户
	public boolean isExistUserByOpenIdAll(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=?",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//根据账号判断User表内是否存在该用户（User表）
	public boolean isExistUserByAccout(String accout){
		List<User> list = User.dao.find("select * from user where accout=?",accout);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//根据账号判断User表内是否存在该用户，除指定账号外（User表）
	public boolean isExistUserByAccout(String accout1,String accout2){
		List<User> list = User.dao.find("select * from user where accout=? and accout!=?",accout2,accout1);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//User表获得最后一条数据的userId
	public int getLastUserId(){
		int id =  Db.queryInt("select id from user order by id desc limit 1");
		return id;
	}
	
	//查询User表内用户信息（User表）
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		Page<User> userPage;
		if(accout == null || accout.equals("")) {
			userPage = User.dao.paginate(pageNumber, everyCount, "select *","from user where exist=?",exist);	
		}else {
			userPage = User.dao.paginate(pageNumber, everyCount, "select *","from user where exist=? and accout like?",exist,"%"+accout+"%");
		}
		return userPage;
	}
	
	//删除User表内单个用户信息（User表修改exist字段为0）
	public boolean deleteOneUser(int userId) {
		boolean succeed = User.dao.findById(userId).set("exist", 0).update();
		return succeed;
	}
	
	//删除UserAuthority表内单个授权信息（UserAuthority表修改exist字段为0）
	public boolean deleteOneUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("exist", 0).update();
		}
		return succeed;
	}
	
	//恢复User表内单个用户信息（User表修改exist字段为1）
	public boolean recoveryOneUser(int userId) {
		boolean succeed = User.dao.findById(userId).set("exist", 1).update();
		return succeed;
	}
	
	//恢复UserAuthority表内单个授权信息（UserAuthority表修改exist字段为1）
	public boolean recoveryOneUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("exist", 1).update();
		}
		return succeed;
	}
	
	//彻底删除User表内用户信息（User表delete）
	public boolean deleteThoroughUser(int userId) {
		boolean succeed = User.dao.deleteById(userId);
		return succeed;
	}
	
	//彻底删除UserAuthority表内授权信息（User表delete）
	public boolean deleteThoroughUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = UserAuthority.dao.deleteById(list.get(0).get("id"));
		}
		return succeed;
	}
	
	//根据用户id获取到要修改的用户信息（账号、别名、头像）
	public User getUpdateUserInfo(int id) {
		User user = User.dao.findById(id);
		return user;
	}

	//修改用户信息（账号、别名、头像）根据修改前账号索引到
	public boolean updateUser(String oldAccout, String newAccout, String nickName, String photo) {
		List<User> list = User.dao.find("select * from user where accout=?",oldAccout);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("accout", newAccout).set("nickName", nickName).set("photo", photo).update();
		}
		return succeed;
	}
	
}
