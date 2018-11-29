package com.mindao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindao.dao.UserDao;
import com.mindao.entity.UserEntity;
import com.mindao.service.UserService;
import com.mindao.utils.MD5Util;
import com.mindao.utils.RRException;
import com.mindao.utils.validator.Assert;



@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserEntity queryObject(String uid){
		return userDao.queryObject(uid);
	}
	
	@Override
	public List<UserEntity> queryList(Map<String, Object> map){
		return userDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDao.queryTotal(map);
	}
	
	@Override
	public void save(UserEntity user){
//		user.setRegisterTime(new Date());
//		user.setUid(uid);
		userDao.save(user);
	}
	
	@Override
	public void update(UserEntity user){
		userDao.update(user);
	}
	
	@Override
	public void delete(String uid){
		userDao.delete(uid);
	}
	
	@Override
	public void deleteBatch(String[] uids){
		userDao.deleteBatch(uids);
	}

//	@Override
//	public UserEntity queryObject(String object) {
//		// TODO Auto-generated method stub
//		return userDao.queryObject(object);
//	}

	@Override
	public void save(String account, String password) {
		UserEntity user = new UserEntity();
		if (account.indexOf("@")>0){
			user.setEmail(account);
		}else{
			user.setMobile(account);
		}
		user.setNickName(account);
		//user.setPassword(DigestUtils.sha256Hex(password));
		user.setPassword(password);
		user.setRegisterTime(new Date());
		user.setStatus(UserEntity.STATUS_ACTIVE);
		userDao.save(user);		
	}

	@Override
	public UserEntity queryByAccount(String account) {
		return userDao.queryByAccount(account);
	}

	@Override
	public String login(String account, String password) {
		UserEntity user = queryByAccount(account);
		Assert.isNull(user, "账号不存在");
		
		 
		if (!UserEntity.STATUS_ACTIVE.equals(user.getStatus())){
			throw new RRException("账号已被冻结");
		}

		//密码错误
//		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
//			throw new RRException("手机号或密码错误");
//		}
 
		String encodePwd=MD5Util.stringMD5(password);
 
		if(!user.getPassword().equalsIgnoreCase(encodePwd)){
			throw new RRException("账号或密码错误");
		}

		return user.getUid();
	}
	
}
