package com.ch.sys.biz.controller.redis
import com.ch.sys.biz.dao.business.entity.employee.Tb_Employee
import com.ch.sys.biz.system.cache.redis.RedisFactoryJson
import com.ch.sys.biz.system.cache.redis.RedisFactoryString
import com.ch.sys.biz.system.results.ServerResultJson
import com.ch.sys.biz.system.utils.JsonUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/redis")
class RedisController {
	
	@Autowired
	private RedisFactoryJson redisFactoryJson
	@Autowired
	private RedisFactoryString redisFactoryString
	
	@GetMapping("/set")
	def set(String value) {
		redisFactoryString.set("str1", value)
		def v = redisFactoryString.get("str1")
		return ServerResultJson.success(v)
	}
	@GetMapping("/set/json")
	def setEntity() {
		Tb_Employee employee = new Tb_Employee()
		employee.setCode("ic20112004")
		employee.setName("Harry.Cheng")
		employee.setAddress("YT ShanDong")
		employee.setEmail("guoqian.cheng@doosan.com")
		employee.setGender(0)
		employee.setJob("IT Dev")
		employee.setMobile("13780924007")
		redisFactoryString.set("employee", JsonUtils.objectToJson(employee))
		String empStr = redisFactoryString.get("employee")
		Tb_Employee re = JsonUtils.jsonToBean(empStr, Tb_Employee)
		return ServerResultJson.success(re)
	}
	@GetMapping("/set/json/entity")
	def setEntityJson() {
		Tb_Employee employee = new Tb_Employee()
		employee.setCode("20112003")
		employee.setName("Jack")
		employee.setAddress("YT ShanDong")
		employee.setEmail("guoqian.cheng@doosan.com")
		employee.setGender(0)
		employee.setJob("IT Dev")
		employee.setMobile("13780924009")
		redisFactoryJson.set("employeeEntity", employee)
		Tb_Employee re = redisFactoryJson.get("employeeEntity")
		return ServerResultJson.success(re)
	}
	
}
