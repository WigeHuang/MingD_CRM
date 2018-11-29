package com.mindao.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2018-02-26 16:32:48
 */
public class TDeskcontactTxlEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String name;
	//
	private String address;
	//
	private String tel;
	//
	private String contactname;
	//
	private String ccode;
	//
	private String email;
	//
	private String bz;
	//
	private String floorname;
	//
	private String deptname;

	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：
	 */
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	/**
	 * 获取：
	 */
	public String getContactname() {
		return contactname;
	}
	/**
	 * 设置：
	 */
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	/**
	 * 获取：
	 */
	public String getCcode() {
		return ccode;
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
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * 设置：
	 */
	public void setFloorname(String floorname) {
		this.floorname = floorname;
	}
	/**
	 * 获取：
	 */
	public String getFloorname() {
		return floorname;
	}
	/**
	 * 设置：
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	/**
	 * 获取：
	 */
	public String getDeptname() {
		return deptname;
	}
}
