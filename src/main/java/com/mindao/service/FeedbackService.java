package com.mindao.service;

import java.util.List;
import java.util.Map;

import com.mindao.entity.FeedbackEntity;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-27 14:47:10
 */
public interface FeedbackService {
	
	FeedbackEntity queryObject(String pid);
	
	List<FeedbackEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FeedbackEntity feedback);
	
	void update(FeedbackEntity feedback);
	
	void delete(String pid);
	
	void deleteBatch(String[] pids);
}
