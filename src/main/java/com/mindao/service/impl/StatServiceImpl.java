package com.mindao.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindao.dao.FeedbackDao;
import com.mindao.dao.UserDao;
import com.mindao.dao.UserDeviceDao;
import com.mindao.service.StatService;
import com.mindao.utils.DateUtils;
@Service("StatService")
public class StatServiceImpl implements StatService {
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserDeviceDao userDeviceDao;
	
	
	@Override
	public Map<String, Object> dashboardStat() {
		Map<String, Object> rtnMap=new HashMap<>();
		String DATE_PATTERN_YM = "yyyy-MM";
	    String DATE_PATTERN_YMD = "yyyy-MM-dd";
	    SimpleDateFormat df_ym = new SimpleDateFormat(DATE_PATTERN_YM);
	    SimpleDateFormat df_ymd = new SimpleDateFormat(DATE_PATTERN_YMD);
 
		
		Date thisMonthStart=DateUtils.getTimesMonthmorning();
		Date thisMonthEnd=DateUtils.getDate(thisMonthStart, Calendar.MONTH, 1);
		Map<String, Object> paraMap=new HashMap<>();
		String[] last6Month=new String[6];
		
 
		for (int i=0;i<6;i++){
			last6Month[i]=df_ym.format(DateUtils.getDate(thisMonthStart, Calendar.MONTH, -5+i));
		}
		rtnMap.put("last6Month",last6Month);
		
		//1.统计用户数据===========================
		Long[] last6MonthUser=new Long[6];
		//1.1新增用户 
		//allUserData=userDao.queryByRegTime(paraMap);
		long allUserCnt=userDao.queryByRegTime(paraMap);
		rtnMap.put("allUser", allUserCnt);
		//1.2用户总数
		paraMap.put("timeStart", df_ymd.format(thisMonthStart));
		paraMap.put("timeEnd", df_ymd.format(thisMonthEnd));
		long thisMonthUser=userDao.queryByRegTime(paraMap);
		rtnMap.put("thisMonthUser", thisMonthUser);
		
		//1.3最近6个月数据
		for (int i=0;i<5;i++){
			paraMap.put("timeStart", last6Month[i]+"-01");
			paraMap.put("timeEnd", last6Month[i+1]+"-01");
 
			last6MonthUser[i]= userDao.queryByRegTime(paraMap);
		}
		last6MonthUser[5]= thisMonthUser;
		rtnMap.put("last6MonthUser",last6MonthUser);
		
		
		
		
		//2.统计 设备数据===========================
		Long[] last6MonthDevice=new Long[6];
		//2.1新增设备 
		paraMap.clear();
		long allDeviceCnt=userDeviceDao.queryByBindTime(paraMap);
		rtnMap.put("allDevice", allDeviceCnt);
		//2.2设备总数
		paraMap.put("timeStart", df_ymd.format(thisMonthStart));
		paraMap.put("timeEnd", df_ymd.format(thisMonthEnd));
		long thisMonthDevice=userDeviceDao.queryByBindTime(paraMap);
		rtnMap.put("thisMonthDevice", thisMonthDevice);
		
		//2.3最近6个月数据
		for (int i=0;i<5;i++){
			paraMap.put("timeStart", last6Month[i]+"-01");
			paraMap.put("timeEnd", last6Month[i+1]+"-01");
			last6MonthDevice[i]= userDeviceDao.queryByBindTime(paraMap);
		}
		last6MonthDevice[5]= thisMonthDevice;
		rtnMap.put("last6MonthDevice",last6MonthDevice);
		
		
		
		
		
		
		
	
		
		//3.统计反馈数据===========================
		Long[] last6MonthFeed=new Long[6];
		//1.1新增用户 
		paraMap.clear();
		long allFeedCnt=feedbackDao.queryBySubmitTime(paraMap);
		rtnMap.put("allFd", allFeedCnt);
		//1.2用户总数
		paraMap.put("timeStart", df_ymd.format(thisMonthStart));
		paraMap.put("timeEnd", df_ymd.format(thisMonthEnd));
		long thisMonthFeed=feedbackDao.queryBySubmitTime(paraMap);
		rtnMap.put("thisMonthFd", thisMonthFeed);
		//1.3最近6个月数据
		for (int i=0;i<5;i++){
			paraMap.put("timeStart", last6Month[i]+"-01");
			paraMap.put("timeEnd", last6Month[i+1]+"-01");
			last6MonthFeed[i]= feedbackDao.queryBySubmitTime(paraMap);
		}
		last6MonthFeed[5]= thisMonthFeed;
		rtnMap.put("last6MonthFd",last6MonthFeed);
 
		return rtnMap;
	}
	
	
	
 
 
}
