package com.mindao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindao.dao.FeedbackDao;
import com.mindao.entity.FeedbackEntity;
import com.mindao.service.FeedbackService;
import com.mindao.utils.MindaoUtils;



@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	public FeedbackEntity queryObject(String pid){
		return feedbackDao.queryObject(pid);
	}
	
	@Override
	public List<FeedbackEntity> queryList(Map<String, Object> map){
		return feedbackDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return feedbackDao.queryTotal(map);
	}
	
	@Override
	public void save(FeedbackEntity feedback){
		feedback.setPid(MindaoUtils.getUuid());
		feedbackDao.save(feedback);
	}
	
	@Override
	public void update(FeedbackEntity feedback){
		feedbackDao.update(feedback);
	}
	
	@Override
	public void delete(String pid){
		feedbackDao.delete(pid);
	}
	
	@Override
	public void deleteBatch(String[] pids){
		feedbackDao.deleteBatch(pids);
	}
	
}
