package  com.farm.user.dao;

import java.net.URLEncoder;
import java.util.List;

import com.farm.model.User;
import com.farm.model.UserAuthority;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class UserDao {
	
	//����openId��ѯUser�����û���Ϣ
	public User findUserByOpenId(String openId){
		List<User> list = User.dao.find("select user.* from user,userAuthority where user.userId=userAuthority.userId and userAuthority.openId=?",openId);
		if(list.size() != 0) {
			User user = list.get(0);
			user.set("photo", URLEncoder.encode(user.getStr("photo")));
			return list.get(0);
		}
		return null;
	}
	
	//����userId��landId��ѯuserCropId
	public int findUcIdByLand(int userId,String landNumber) {
		List<User> list = User.dao.find("select * from user where id = "+userId);
		if(list.size()!=0) {
			User user = list.get(0);
			int ucId = user.getInt(landNumber);
			return ucId;
		}
		return 0;
	}
	
	//User����������ͷ��nickName��photo��
	public boolean addUser(String nickName, String photo){
		boolean succeed =  new User().set("nickName", nickName).set("photo", photo).set("level", 1).set("experience", 0).set("money", 0).set("online", 1).save();
		return succeed;
	}
	//User�����ӽ�ˮ��ʩ�ʴ�����water��fertilizer��
	public void addWaterAndFertilizer(int id, int water, int fertilizer) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("water", user.getInt("water")+water).update();
			User.dao.set("fertilizer", user.getInt("fertilizer")+fertilizer).update();
		}
	}
	//User�����Ӿ��飬��ң�experience��money��
	public void addExandMoney(int id,int ex,int money) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("experience", user.getInt("experience")+ex).set("money", user.getInt("money")+money).update();
		}
	}
	//User����ٽ�ˮ��water��
	public void lessWater(int id) {
		List<User> list = User.dao.find("select * from user where id = "+id);
		if(list.size()!=0) {
			User user = list.get(0);
			User.dao.set("water", user.getInt("water")-1).update();
		}
	}
	//User�����ʩ�ʣ�fertilizer��
		public void lessFertilizer(int id) {
			List<User> list = User.dao.find("select * from user where id = "+id);
			if(list.size()!=0){
				User user = list.get(0);
				User.dao.set("fertilizer", user.getInt("fertilizer")-1).update();
			}
		}
	
	//UserAuthority���ڲ���userId��openId��token
	public boolean addUserAuthority(int userId, String openId){
		boolean succeed =  new UserAuthority().set("userId", userId).set("openId", openId).set("type", "QQ").save();
		return succeed;
	}		
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û�exist=1
	public boolean isExistUserByOpenId(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=? and exist=1",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û�
	public boolean isExistUserByOpenIdAll(String openId){
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where openId=?",openId);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//�����˺��ж�User�����Ƿ���ڸ��û���User��
	public boolean isExistUserByAccout(String accout){
		List<User> list = User.dao.find("select * from user where accout=?",accout);
		if(list.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//�����˺��ж�User�����Ƿ���ڸ��û�����ָ���˺��⣨User��
	public boolean isExistUserByAccout(String accout1,String accout2){
		List<User> list = User.dao.find("select * from user where accout=? and accout!=?",accout2,accout1);
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
	
	//��ѯUser�����û���Ϣ��User��
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		Page<User> userPage;
		if(accout == null || accout.equals("")) {
			userPage = User.dao.paginate(pageNumber, everyCount, "select *","from user where exist=?",exist);	
		}else {
			userPage = User.dao.paginate(pageNumber, everyCount, "select *","from user where exist=? and accout like?",exist,"%"+accout+"%");
		}
		return userPage;
	}
	
	//ɾ��User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneUser(int userId) {
		boolean succeed = User.dao.findById(userId).set("exist", 0).update();
		return succeed;
	}
	
	//ɾ��UserAuthority���ڵ�����Ȩ��Ϣ��UserAuthority���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("exist", 0).update();
		}
		return succeed;
	}
	
	//�ָ�User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneUser(int userId) {
		boolean succeed = User.dao.findById(userId).set("exist", 1).update();
		return succeed;
	}
	
	//�ָ�UserAuthority���ڵ�����Ȩ��Ϣ��UserAuthority���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("exist", 1).update();
		}
		return succeed;
	}
	
	//����ɾ��User�����û���Ϣ��User��delete��
	public boolean deleteThoroughUser(int userId) {
		boolean succeed = User.dao.deleteById(userId);
		return succeed;
	}

	//����ɾ��UserAuthority������Ȩ��Ϣ��User��delete��
	public boolean deleteThoroughUserAuthority(int userId) {
		List<UserAuthority> list = UserAuthority.dao.find("select * from userAuthority where userId=?",userId);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = UserAuthority.dao.deleteById(list.get(0).get("id"));
		}
		return succeed;
	}
	
	//�����û�id��ȡ��Ҫ�޸ĵ��û���Ϣ���˺š�������ͷ��
	public User getUpdateUserInfo(int id) {
		User user = User.dao.findById(id);
		return user;
	}

	//�޸��û���Ϣ���˺š�������ͷ�񣩸����޸�ǰ�˺�������
	public boolean updateUser(String oldAccout, String newAccout, String nickName, String photo) {
		List<User> list = User.dao.find("select * from user where accout=?",oldAccout);
		boolean succeed = false;
		if(list.size() != 0) {
			succeed = list.get(0).set("accout", newAccout).set("nickName", nickName).set("photo", photo).update();
		}
		return succeed;
	}
	
}
