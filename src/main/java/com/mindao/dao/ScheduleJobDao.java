package com.mindao.dao;

import java.util.Map;

import com.mindao.entity.ScheduleJobEntity;

/**
 * 定时任务
 * 
 * @author ligc
 * @email 153277817@qq.com
 * @date 2016年12月1日 下午10:29:57
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
