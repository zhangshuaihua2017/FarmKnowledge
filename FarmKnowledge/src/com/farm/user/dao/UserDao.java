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
		List<User> list = User.dao.find("select user.* from user,userAuthority where user.userId=userAuthority.userId and userAuthority.openId=?",openId);
		if(list.size() != 0) {
			User user = list.get(0);
			user.set("photo", URLEncoder.encode(user.getStr("photo")));
			return list.get(0);
		}
		return null;
	}
	
	//根据userId，landId查询userCropId
	public int findUcIdByLand(int userId,String landNumber) {
		List<User> list = User.dao.find("select * from user where id = "+userId);
		if(list.size()!=0) {
			User user = list.get(0);
			int ucId = user.getInt(landNumber);
			return ucId;
		}
		return 0;
	}
	
	//User表插入别名、头像（nickName、photo）
	public boolean addUser(String nickName, String photo){
		boolean succeed =  new User().set("nickName", nickName).set("photo", photo).set("level", 1).set("experience", 0).set("money", 0).set("online", 1).save();
		return succeed;
	}
	//User表增加浇水、施肥次数（water、fertilizer）
	public void addWaterAndFertilizer(int id, int water, int fertilizer) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("water", user.getInt("water")+water).update();
			User.dao.set("fertilizer", user.getInt("fertilizer")+fertilizer).update();
		}
	}
	//User表增加经验，金币（experience，money）
	public void addExandMoney(int id,int ex,int money) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("experience", user.getInt("experience")+ex).set("money", user.getInt("money")+money).update();
		}
	}
	//User表减少浇水（water）
	public void lessWater(int id) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("water", user.getInt("water")-1).update();
		}
	}
	//User表减少施肥（fertilizer）
		public void lessFertilizer(int id) {
			List<User> list = User.dao.find("select * from user where id = "+id);
			if(list.size()!=0){
				User user = list.get(0);
				User.dao.set("fertilizer", user.getInt("fertilizer")-1).update();
			}
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
	
	//恢复User表内单个用户信息（User表修改exist字段为1）
	public boolean recoveryOneUser(int userId) {
		boolean succeed = User.dao.findById(userId).set("exist", 1).update();
		return succeed;
	}
	
	//彻底删除User表内用户信息（User表delete）
	public boolean deleteThoroughUser(int userId) {
		boolean succeed = User.dao.deleteById(userId);
		return succeed;
	}
	
}
