package  com.farm.user.service;

import java.sql.SQLException;

import com.farm.model.User;
import com.farm.user.dao.UserDao;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

public class UserService {
	
	//����openId��ѯUser�����û���Ϣ��User��UserAuthority��
	public User findUserByOpenId(String openId){
        return new UserDao().findUserByOpenId(openId);
    }
	
	//������û���User��UserAuthority��
	public boolean addUser(String openId, String nickName, String photo){
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				UserDao userDao = new UserDao();
				
				//user����������ͷ��
				boolean a1 = userDao.addUser(nickName, photo);
				//���user��ղ������ݵ�userId
				int userId = userDao.getLastUserId();
				//UserAuthority���ڲ���userId��openId��token
				boolean a2 = userDao.addUserAuthority(userId, openId);
				
				if(a1 == true && a2 == true) { //��ӳɹ�
					return true;
				}else { //���ʧ�ܣ�����ص�
					return false;
				}
			}
		});
		return succeed;
    }
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û���UserAuthority��
	public boolean isExistUser(String openId){
        return new UserDao().isExistUser(openId);
    }

	//��ѯUser�����û���Ϣ��User��
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		return new UserDao().findUserPage(pageNumber,everyCount,accout,exist);
	}
	
	//ɾ��User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneUser(int userId) {
		return new UserDao().deleteOneUser(userId);
	}
	
	//�ָ�User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneUser(int userId) {
		return new UserDao().recoveryOneUser(userId);
	}
	
	//����ɾ��User�����û���Ϣ��User��delete��
	public boolean deleteThoroughUser(int userId) {
		return new UserDao().deleteThoroughUser(userId);
	}
	
}
