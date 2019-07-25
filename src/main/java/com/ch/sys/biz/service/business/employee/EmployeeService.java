package com.ch.sys.biz.service.business.employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ch.sys.biz.dao.business.entity.employee.Tb_Employee;
import com.ch.sys.biz.dao.business.mapper.employee.EmployeeDao;
import com.ch.sys.biz.service.base.BaseService;
import com.ch.sys.biz.system.exception.ServerException;
import com.ch.sys.biz.system.results.ServerResults;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class EmployeeService extends BaseService<Tb_Employee, EmployeeDao> {
	
	private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeDao employeeDao;
	@Transactional(propagation=Propagation.SUPPORTS)
	public int recordCntByTerm(String name){
		try{
			return employeeDao.recordCntByTerm(name);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new ServerException(ServerResults.ERROR);
		}
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public Page<Tb_Employee> findAllByTerm(String order, Integer page, Integer pageSize, String name){
		try{
			if(page == null)
				page = 1;
			if(pageSize == null)
				pageSize = 10;
			Page<Tb_Employee> pageHelper = PageHelper.startPage(page, pageSize, order);
			return employeeDao.findAllByTerm(name);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new ServerException(ServerResults.ERROR);
		}
	}
}