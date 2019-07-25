package com.ch.sys.biz.dao.business.mapper.employee;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;
import com.ch.sys.biz.dao.base.mapper.BaseMapper;
import com.ch.sys.biz.dao.business.entity.employee.Tb_Employee;
@Mapper
public interface EmployeeDao extends BaseMapper<Tb_Employee> {
	/**
	 * 根据name获取数据条数
	 * @param name
	 * @return
	 */
	int recordCntByTerm(String name);
	/**
	 * 根据name查找全部
	 * @param entity
	 * @return
	 */
	Page<Tb_Employee> findAllByTerm(String name);
}
