package com.ch.sys.biz.dao.business.entity.department;
import com.ch.sys.biz.dao.base.entity.BaseEntity;

public class Tb_Department extends BaseEntity {

	private static final long serialVersionUID = -1912323687425829768L;
	private String name;		//部门名称
	private String code;		//部门代码
	private Integer parentid;	//上级部门ID
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}