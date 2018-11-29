package com.mindao.dao;

import java.util.Map;

import com.mindao.entity.UserEntity;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
public interface UserDao extends BaseDao<UserEntity> {

	UserEntity queryByAccount(String account);
	
	long queryByRegTime(Map<String, Object> map);
}
