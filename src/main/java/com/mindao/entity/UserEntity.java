package com.mindao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String STATUS_ACTIVE="1";
	public static final String STATUS_INACTIVE="0";
	//
	private String uid;
	//
	private String email;
	//
	private String mobile;
	//MD5加密
	private String password;
	//0-未验证 1-验证
	private String emailFlag;
	//0-绑定 1-绑定
	private String mobileFlag;
	//按照普通路径存储
	private String avatar;
	

	
	//
	private String nickName;
	//0-未锁住 1-锁住
	private String isLocked;
	//
	private String retryTimes;
	//
	private Date registerTime;
	//CN-中国 USA-美国
	private String registerRegion;
	//
	private Date lastModifyTime;
	//
	private Integer lid;
	//S01-IPC S02-JustFit S03-SmartThemetre
	private String registerFrom;
	//
	private String activeCode;
	//
	private Date lastActiveTime;
	//0-未激活 1-激活
	private String status;
	//注册类型：0:email,1:mobile
	private String accType;
	//
	private Float timezone;
	//手机类型1苹果，2安卓，3微软mp
	private Integer mobileType;
	//消息平台类型:10苹果原生20Google原生21 极光24小米25华为…
	private Integer msgPlatformType;
	//云消息平台的注册id
	private String msgRegId;
	//
	private Date msgRegTime;
	//手机设备id
	private String mobileDeviceId;
	//
	private Date loginTime;
	//
	private Integer loginState;
	
	//前台用的字段=====================================
	//按照普通路径存储,全路径，
	private String avatarPath;
	//0-未激活 1-激活
//	private String statusName;
	
	private  List<UserDeviceEntity>  devices;

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
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：MD5加密
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：MD5加密
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：0-未验证 1-验证
	 */
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	/**
	 * 获取：0-未验证 1-验证
	 */
	public String getEmailFlag() {
		return emailFlag;
	}
	/**
	 * 设置：0-绑定 1-绑定
	 */
	public void setMobileFlag(String mobileFlag) {
		this.mobileFlag = mobileFlag;
	}
	/**
	 * 获取：0-绑定 1-绑定
	 */
	public String getMobileFlag() {
		return mobileFlag;
	}
	/**
	 * 设置：S3:开头按照S3存储
NOR:开头按照普通路径存储
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：S3:开头按照S3存储
NOR:开头按照普通路径存储
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置：
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：0-未锁住 1-锁住
	 */
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	/**
	 * 获取：0-未锁住 1-锁住
	 */
	public String getIsLocked() {
		return isLocked;
	}
	/**
	 * 设置：
	 */
	public void setRetryTimes(String retryTimes) {
		this.retryTimes = retryTimes;
	}
	/**
	 * 获取：
	 */
	public String getRetryTimes() {
		return retryTimes;
	}
	/**
	 * 设置：
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：CN-中国 USA-美国
	 */
	public void setRegisterRegion(String registerRegion) {
		this.registerRegion = registerRegion;
	}
	/**
	 * 获取：CN-中国 USA-美国
	 */
	public String getRegisterRegion() {
		return registerRegion;
	}
	/**
	 * 设置：
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	/**
	 * 设置：
	 */
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	/**
	 * 获取：
	 */
	public Integer getLid() {
		return lid;
	}
	/**
	 * 设置：S01-IPC S02-JustFit S03-SmartThemetre
	 */
	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}
	/**
	 * 获取：S01-IPC S02-JustFit S03-SmartThemetre
	 */
	public String getRegisterFrom() {
		return registerFrom;
	}
	/**
	 * 设置：
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	/**
	 * 获取：
	 */
	public String getActiveCode() {
		return activeCode;
	}
	/**
	 * 设置：
	 */
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	/**
	 * 设置：0-未激活 1-激活
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：0-未激活 1-激活
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：注册类型：0:email,1:mobile
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}
	/**
	 * 获取：注册类型：0:email,1:mobile
	 */
	public String getAccType() {
		return accType;
	}
	/**
	 * 设置：
	 */
	public void setTimezone(Float timezone) {
		this.timezone = timezone;
	}
	/**
	 * 获取：
	 */
	public Float getTimezone() {
		return timezone;
	}
	/**
	 * 设置：手机类型1苹果，2安卓，3微软mp
	 */
	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}
	/**
	 * 获取：手机类型1苹果，2安卓，3微软mp
	 */
	public Integer getMobileType() {
		return mobileType;
	}
	/**
	 * 设置：消息平台类型:10苹果原生20Google原生21 极光24小米25华为…
	 */
	public void setMsgPlatformType(Integer msgPlatformType) {
		this.msgPlatformType = msgPlatformType;
	}
	/**
	 * 获取：消息平台类型:10苹果原生20Google原生21 极光24小米25华为…
	 */
	public Integer getMsgPlatformType() {
		return msgPlatformType;
	}
	/**
	 * 设置：云消息平台的注册id
	 */
	public void setMsgRegId(String msgRegId) {
		this.msgRegId = msgRegId;
	}
	/**
	 * 获取：云消息平台的注册id
	 */
	public String getMsgRegId() {
		return msgRegId;
	}
	/**
	 * 设置：
	 */
	public void setMsgRegTime(Date msgRegTime) {
		this.msgRegTime = msgRegTime;
	}
	/**
	 * 获取：
	 */
	public Date getMsgRegTime() {
		return msgRegTime;
	}
	/**
	 * 设置：手机设备id
	 */
	public void setMobileDeviceId(String mobileDeviceId) {
		this.mobileDeviceId = mobileDeviceId;
	}
	/**
	 * 获取：手机设备id
	 */
	public String getMobileDeviceId() {
		return mobileDeviceId;
	}
	/**
	 * 设置：
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置：
	 */
	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}
	/**
	 * 获取：
	 */
	public Integer getLoginState() {
		return loginState;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getStatusName() {
		if (STATUS_ACTIVE.equals(status)){
			return "已激活(active)";	
		}else{
			return "未激活(inactive)";
		}
	}
//	public void setStatusName(String statusName) {
//		this.statusName = statusName;
//	}
	public List<UserDeviceEntity> getDevices() {
		return devices;
	}
	public void setDevices(List<UserDeviceEntity> devices) {
		this.devices = devices;
	}
	
	
}
