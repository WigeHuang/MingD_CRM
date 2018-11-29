package com.mindao.entity;

import java.io.Serializable;
import java.util.Date;
 


/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-27 14:47:10
 */
public class FeedbackEntity implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final int STATE_PENDING=100;
	public static final int STATE_HANDLING=200;
	public static final int STATE_INVALID=250;
	public static final int STATE_SOLVED=300;
	//
	private String pid;
	//
	private String uid;
	//
	private String feedback;
	//
	private Date submitDate;
	//
	private Long handler;
	//
	private String handleResult;
	//
	private Date handleDate;
	
	private Integer state;
	
 
	//联系方式
	private String contact;
	//是否上传日志
	private Integer uploadLog;	
	//日志信息
	private String log;
	//日志信息
	private String logFile;
//	//是否有效反馈,作废，使用state里STATE_INVALID代替
//	private Integer effective;	
	
	
	//关联用户表数据，前台用
	private String email;
	private String mobile;
	private String nickname;
	private String handlerName;

	/**
	 * 设置：
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置：
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	/**
	 * 获取：
	 */
	public String getFeedback() {
		return feedback;
	}
	/**
	 * 设置：
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	/**
	 * 获取：
	 */
	public Date getSubmitDate() {
		return submitDate;
	}
	/**
	 * 设置：
	 */
	public void setHandler(Long handler) {
		this.handler = handler;
	}
	/**
	 * 获取：
	 */
	public Long getHandler() {
		return handler;
	}
	/**
	 * 设置：
	 */
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	/**
	 * 获取：
	 */
	public String getHandleResult() {
		return handleResult;
	}
	/**
	 * 设置：
	 */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	/**
	 * 获取：
	 */
	public Date getHandleDate() {
		return handleDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getUploadLog() {
		return uploadLog;
	}
	public void setUploadLog(Integer uploadLog) {
		this.uploadLog = uploadLog;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	
}
