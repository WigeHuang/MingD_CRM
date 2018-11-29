package com.mindao.dao;

import java.util.Map;

import com.mindao.entity.FeedbackEntity;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-27 14:47:10
 */
public interface FeedbackDao extends BaseDao<FeedbackEntity> {
	long queryBySubmitTime(Map<String, Object> map);
}
