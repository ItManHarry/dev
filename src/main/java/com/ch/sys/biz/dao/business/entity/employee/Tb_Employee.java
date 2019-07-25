package com.ch.sys.biz.dao.business.entity.employee;
import com.ch.sys.biz.dao.base.entity.BaseEntity;

public class Tb_Employee extends BaseEntity {

	/**
	 * 性别(男 : 0, 女 : 1)
	 */
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	private static final long serialVersionUID = 5375541256532181851L;
	private String name;	//姓名
	private String code;	//职号
	private int gender;		//性别
	private String email;	//邮箱
	private String mobile;	//手机号
	private String address;	//住址
	private String job;		//职位
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}