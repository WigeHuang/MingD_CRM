package com.mindao.service;

import java.util.List;
import java.util.Map;

import com.mindao.entity.UserDeviceEntity;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
public interface UserDeviceService {
	
	UserDeviceEntity queryObject(String pid);
	
	List<UserDeviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserDeviceEntity userDevice);
	
	void update(UserDeviceEntity userDevice);
	
	void delete(String pid);
	
	void deleteBatch(String[] pids);
	
	List<UserDeviceEntity> queryByUserId(Map<String, Object> map);
}
