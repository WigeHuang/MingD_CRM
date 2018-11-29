package com.mindao.dao;

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
public interface UserDeviceDao extends BaseDao<UserDeviceEntity> {

	List<UserDeviceEntity> queryByUserId(Map<String, Object> map);
	
	long queryByBindTime(Map<String, Object> map);
	
}
