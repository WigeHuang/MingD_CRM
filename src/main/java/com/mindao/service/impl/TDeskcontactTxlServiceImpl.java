package com.mindao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.mindao.dao.TDeskcontactTxlDao;
import com.mindao.entity.TDeskcontactTxlEntity;
import com.mindao.service.TDeskcontactTxlService;



@Service("tDeskcontactTxlService")
public class TDeskcontactTxlServiceImpl implements TDeskcontactTxlService {
	@Autowired
	private TDeskcontactTxlDao tDeskcontactTxlDao;
	
	@Override
	public TDeskcontactTxlEntity queryObject(String id){
		return tDeskcontactTxlDao.queryObject(id);
	}
	
	@Override
	public List<TDeskcontactTxlEntity> queryList(Map<String, Object> map){
		return tDeskcontactTxlDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDeskcontactTxlDao.queryTotal(map);
	}
	
	@Override
	public void save(TDeskcontactTxlEntity tDeskcontactTxl){
		tDeskcontactTxlDao.save(tDeskcontactTxl);
	}
	
	@Override
	public void update(TDeskcontactTxlEntity tDeskcontactTxl){
		tDeskcontactTxlDao.update(tDeskcontactTxl);
	}
	
	@Override
	public void delete(String id){
		tDeskcontactTxlDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		tDeskcontactTxlDao.deleteBatch(ids);
	}
	
}
