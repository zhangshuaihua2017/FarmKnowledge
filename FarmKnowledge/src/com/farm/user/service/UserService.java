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
				boolean a1 = userDao.addUser(nickName, photo);
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
	
	//根据openId判断UserAuthority表内是否存在该用户（UserAuthority表）
	public boolean isExistUser(String openId){
        return new UserDao().isExistUser(openId);
    }

	//查询User表内用户信息（User表）
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		return new UserDao().findUserPage(pageNumber,everyCount,accout,exist);
	}
	
	//删除User表内单个用户信息（User表修改exist字段为0）
	public boolean deleteOneUser(int userId) {
		return new UserDao().deleteOneUser(userId);
	}
	
	//恢复User表内单个用户信息（User表修改exist字段为1）
	public boolean recoveryOneUser(int userId) {
		return new UserDao().recoveryOneUser(userId);
	}
	
	//彻底删除User表内用户信息（User表delete）
	public boolean deleteThoroughUser(int userId) {
		return new UserDao().deleteThoroughUser(userId);
	}
	
}
