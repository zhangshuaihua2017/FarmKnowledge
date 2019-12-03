package  com.farm.user.service;

import java.sql.SQLException;

import com.farm.model.User;
import com.farm.user.dao.UserDao;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class UserService {
	
	//根据openId查询User表内用户信息（User表、UserAuthority表）
	public User findUserByOpenId(String openId){
        		return new UserDao().findUserByOpenId(openId);
    	}
	
	//添加新用户（User表、UserAuthority表）
	public boolean addUser(String openId, String nickName, String photo){
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				UserDao userDao = new UserDao();
				
				//user表插入别名、头像
				boolean a1 = userDao.addUser(generateAccout(), nickName, photo);
				//获得user表刚插入数据的userId
				int userId = userDao.getLastUserId();
				//UserAuthority表内插入userId、openId、token
				boolean a2 = userDao.addUserAuthority(userId, openId);
				
				if(a1 == true && a2 == true) { //添加成功
					return true;
				}else { //添加失败，事务回调
					return false;
				}
			}
		});
		return succeed;
   	 }
	
	//根据openId判断UserAuthority表内是否存在该用户exist=1（UserAuthority表）
	public boolean isExistUserByOpenId(String openId){
        		return new UserDao().isExistUserByOpenId(openId);
    	}
	
	//根据openId判断UserAuthority表内是否存在该用户（UserAuthority表）
	public boolean isExistUserByOpenIdAll(String openId){
		return new UserDao().isExistUserByOpenIdAll(openId);
	}
	
	//根据账号判断User表内是否存在该用户（User表）
	public boolean isExistUserByAccout(String accout){
        		return new UserDao().isExistUserByAccout(accout);
	}

	//添加浇水，施肥（User表）
	public void addWaterAndFer(int id,int water, int fertilizer) {
		new UserDao().addWaterAndFertilizer(id, water, fertilizer);
	}
	//添加用户经验，金币(User表）
	public void addEandM(int id,int ex,int money) {
		new UserDao().addExandMoney(id, ex, money);
	}
	//减少浇水（User表）
	public void lessW(int id) {
		new UserDao().lessWater(id);
	}
	//减少施肥（User表）
	public void lessF(int id) {
		new UserDao().lessFertilizer(id);
	}
	public int findUcId(int userId,String landNumber) {
		return new UserDao().findUcIdByLand(userId, landNumber);
	}
	
	//根据账号判断User表内是否存在该用户，除指定账号外（User表）
	public boolean isExistUserByAccout(String accout1,String accout2){
        		return new UserDao().isExistUserByAccout(accout1,accout2);
    	}

	//查询User表内用户信息（User表）
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		return new UserDao().findUserPage(pageNumber,everyCount,accout,exist);
	}
	
	//删除User表内单个用户信息（User表修改exist字段为0）
	public boolean deleteOneUser(int userId) {
		return new UserDao().deleteOneUser(userId);
	}
	
	//删除UserAuthority表内单个授权信息（UserAuthority表修改exist字段为0）
	public boolean deleteOneUserAuthority(int userId) {
		return new UserDao().deleteOneUserAuthority(userId);
	}
	
	//恢复User表内单个用户信息（User表修改exist字段为1）
	public boolean recoveryOneUser(int userId) {
		return new UserDao().recoveryOneUser(userId);
	}
	
	//恢复UserAuthority表内单个授权信息（UserAuthority表修改exist字段为1）
	public boolean recoveryOneUserAuthority(int userId) {
		return new UserDao().recoveryOneUserAuthority(userId);
	}
	
	//彻底删除User表内用户信息（User表delete）
	public boolean deleteThoroughUser(int userId) {
		return new UserDao().deleteThoroughUser(userId);
	}
	
	//彻底删除UserAuthority表内授权信息（User表delete）
	public boolean deleteThoroughUserAuthority(int userId) {
		return new UserDao().deleteThoroughUserAuthority(userId);
	}
	
	//生成账号
	public String generateAccout() {
		String accout = "";
		do{
			accout = "";
			for(int n = 1;n < 9;n++) {
				accout += (int)(Math.random()*10);
			}
		}while(isExistUserByAccout(accout));
		return accout;
	}

	//根据用户id获取到要修改的用户信息（账号、别名、头像）
	public User getUpdateUserInfo(int id) {
		return new UserDao().getUpdateUserInfo(id);
	}
	
	//修改用户信息（账号、别名、头像）根据修改前账号索引到
	public boolean updateUser(String oldAccout, String newAccout, String nickName, String photo) {
		return new UserDao().updateUser(oldAccout, newAccout, nickName, photo);
	}

}
