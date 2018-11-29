package com.mindao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindao.dao.UserDeviceDao;
import com.mindao.entity.UserDeviceEntity;
import com.mindao.service.UserDeviceService;

import java.util.List;
import java.util.Map;



@Service("userDeviceService")
public class UserDeviceServiceImpl implements UserDeviceService {
	@Autowired
	private UserDeviceDao userDeviceDao;
	
	@Override
	public UserDeviceEntity queryObject(String pid){
		return userDeviceDao.queryObject(pid);
	}
	
	@Override
	public List<UserDeviceEntity> queryList(Map<String, Object> map){
		return userDeviceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDeviceDao.queryTotal(map);
	}
	
	@Override
	public void save(UserDeviceEntity userDevice){
		userDeviceDao.save(userDevice);
	}
	
	@Override
	public void update(UserDeviceEntity userDevice){
		userDeviceDao.update(userDevice);
	}
	
	@Override
	public void delete(String pid){
		userDeviceDao.delete(pid);
	}
	
	@Override
	public void deleteBatch(String[] pids){
		userDeviceDao.deleteBatch(pids);
	}
	
	@Override
	public List<UserDeviceEntity> queryByUserId(Map<String, Object> map){
		return userDeviceDao.queryByUserId(map);
	}
	
}
