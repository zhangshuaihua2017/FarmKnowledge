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
				boolean a1 = userDao.addUser(generateAccout(), nickName, photo);
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
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û�exist=1��UserAuthority��
	public boolean isExistUserByOpenId(String openId){
        		return new UserDao().isExistUserByOpenId(openId);
    	}
	
	//����openId�ж�UserAuthority�����Ƿ���ڸ��û���UserAuthority��
	public boolean isExistUserByOpenIdAll(String openId){
		return new UserDao().isExistUserByOpenIdAll(openId);
	}
	
	//�����˺��ж�User�����Ƿ���ڸ��û���User��
	public boolean isExistUserByAccout(String accout){
        		return new UserDao().isExistUserByAccout(accout);
	}

	//��ӽ�ˮ��ʩ�ʣ�User��
	public void addWaterAndFer(int id,int water, int fertilizer) {
		new UserDao().addWaterAndFertilizer(id, water, fertilizer);
	}
	//����û����飬���(User��
	public void addEandM(int id,int ex,int money) {
		new UserDao().addExandMoney(id, ex, money);
	}
	//���ٽ�ˮ��User��
	public void lessW(int id) {
		new UserDao().lessWater(id);
	}
	//����ʩ�ʣ�User��
	public void lessF(int id) {
		new UserDao().lessFertilizer(id);
	}
	public int findUcId(int userId,String landNumber) {
		return new UserDao().findUcIdByLand(userId, landNumber);
	}
	
	//�����˺��ж�User�����Ƿ���ڸ��û�����ָ���˺��⣨User��
	public boolean isExistUserByAccout(String accout1,String accout2){
        		return new UserDao().isExistUserByAccout(accout1,accout2);
    	}

	//��ѯUser�����û���Ϣ��User��
	public Page<User> findUserPage(int pageNumber,int everyCount,String accout,int exist) {
		return new UserDao().findUserPage(pageNumber,everyCount,accout,exist);
	}
	
	//ɾ��User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneUser(int userId) {
		return new UserDao().deleteOneUser(userId);
	}
	
	//ɾ��UserAuthority���ڵ�����Ȩ��Ϣ��UserAuthority���޸�exist�ֶ�Ϊ0��
	public boolean deleteOneUserAuthority(int userId) {
		return new UserDao().deleteOneUserAuthority(userId);
	}
	
	//�ָ�User���ڵ����û���Ϣ��User���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneUser(int userId) {
		return new UserDao().recoveryOneUser(userId);
	}
	
	//�ָ�UserAuthority���ڵ�����Ȩ��Ϣ��UserAuthority���޸�exist�ֶ�Ϊ1��
	public boolean recoveryOneUserAuthority(int userId) {
		return new UserDao().recoveryOneUserAuthority(userId);
	}
	
	//����ɾ��User�����û���Ϣ��User��delete��
	public boolean deleteThoroughUser(int userId) {
		return new UserDao().deleteThoroughUser(userId);
	}
	
	//����ɾ��UserAuthority������Ȩ��Ϣ��User��delete��
	public boolean deleteThoroughUserAuthority(int userId) {
		return new UserDao().deleteThoroughUserAuthority(userId);
	}
	
	//�����˺�
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

	//�����û�id��ȡ��Ҫ�޸ĵ��û���Ϣ���˺š�������ͷ��
	public User getUpdateUserInfo(int id) {
		return new UserDao().getUpdateUserInfo(id);
	}
	
	//�޸��û���Ϣ���˺š�������ͷ�񣩸����޸�ǰ�˺�������
	public boolean updateUser(String oldAccout, String newAccout, String nickName, String photo) {
		return new UserDao().updateUser(oldAccout, newAccout, nickName, photo);
	}

}
