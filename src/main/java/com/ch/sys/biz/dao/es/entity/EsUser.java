package com.ch.sys.biz.dao.es.entity;
import java.io.Serializable;

public class EsUser implements Serializable{	
	
	private static final long serialVersionUID = -5013676039272947181L;
	private String name;
	private int sex;
	private int age;
	private String book;
	private String remark;
	
	public EsUser() {}
	
	public EsUser(String name, int sex, int age, String book, String remark) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.book = book;
		this.remark = remark;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
}
