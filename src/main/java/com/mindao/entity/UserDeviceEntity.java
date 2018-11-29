package com.mindao.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
public class UserDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String DST_TRUE="1";
	public static final String DST_FALSE="0";
	
	//主键id
	private String pid;
	//用户id
	private String userId;
	//产品名称
	private String deviceName;
	//型号代码
	private String modelCode;
	//设备唯一标识
	private String deviceId;
	//产品二维码
	private String qrCode;
	//
	private Date bindTime;
	
	private String city;
	private String region;
	private String country;
	private Double timezone;
	//该区是否使用夏令时:0否，1是
	private Integer dst;
	
	
	//关联用户表数据，前台用
	private String dstFlag;
	private String email;
	private String mobile;
	private String nickname;
	/**
	 * 设置：主键id
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取：主键id
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：产品名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * 设置：型号代码
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	/**
	 * 获取：型号代码
	 */
	public String getModelCode() {
		return modelCode;
	}
	/**
	 * 设置：设备唯一标识
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备唯一标识
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：产品二维码
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	/**
	 * 获取：产品二维码
	 */
	public String getQrCode() {
		return qrCode;
	}
	/**
	 * 设置：
	 */
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	/**
	 * 获取：
	 */
	public Date getBindTime() {
		return bindTime;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getTimezone() {
		return timezone;
	}
	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}
	public Integer getDst() {
		return dst;
	}
	public void setDst(Integer dst) {
		this.dst = dst;
	}
	public String getDstFlag() {
		if (DST_TRUE.equals(dst)){
			return "是(true)";
		}else{
			return "是(false)";
		}
	}
 
	
	
}
