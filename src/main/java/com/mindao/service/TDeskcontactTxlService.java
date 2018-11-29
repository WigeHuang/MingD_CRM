package com.mindao.service;

import com.mindao.entity.TDeskcontactTxlEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2018-02-26 16:32:48
 */
public interface TDeskcontactTxlService {
	
	TDeskcontactTxlEntity queryObject(String id);
	
	List<TDeskcontactTxlEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDeskcontactTxlEntity tDeskcontactTxl);
	
	void update(TDeskcontactTxlEntity tDeskcontactTxl);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
