package com.mindao.service;

import java.util.List;
import java.util.Map;

import com.mindao.entity.UserEntity;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
public interface UserService {
	
	UserEntity queryObject(String uid);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(String uid);
	
	void deleteBatch(String[] uids);

	//UserEntity queryObject(Long object);

	void save(String account, String password);

	String login(String account, String password);
	
	public UserEntity queryByAccount(String account);
}
