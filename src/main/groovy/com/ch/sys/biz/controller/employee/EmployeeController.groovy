package com.ch.sys.biz.controller.employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ch.sys.biz.dao.business.entity.employee.Tb_Employee
import com.ch.sys.biz.service.business.employee.EmployeeService
import com.ch.sys.biz.system.results.ServerResultJson
import com.github.pagehelper.Page
@Controller
@RequestMapping("/biz/employee")
class EmployeeController {

	final String URL = "biz/employee"
	@Autowired
	EmployeeService employeeService
	/**
	 * 跳转至清单画面
	 * @return
	 */
	@GetMapping("/list")
	def list(Map map){
		def total = employeeService.recordCntByTerm("");
		map.put("total", total)
		return URL + "/list"
	}
	/**
	 * 跳转至新增画面
	 * @return
	 */
	@GetMapping("/add")
	def add(){
		return URL + "/add"
	}
	/**
	 * 执行保存
	 * @param employee
	 * @return
	 */
	@PostMapping("/save")
	def save(String name, String job, Map map){
		Tb_Employee employee = new Tb_Employee()
		employee.setName(name)
		employee.setJob(job)
		employee.setCreateuserid("20112004")
		employee.setNewRecord(true)
		employeeService.save(employee)
		def total = employeeService.recordCntByTerm("");
		map.put("total", total)
		return URL + "/list"
	}
	/**
	 * 跳转至修改画面
	 * @param id
	 * @param map
	 * @return
	 */
	@GetMapping("/update")
	def update(String id, Map map){
		Tb_Employee employee = employeeService.findById(id)
		println employee.id + ", "  + employee.name + ", " + employee.job
		map.put("employee", employee)
		return URL + "/update"
	}
	/**
	 * 执行更新
	 * @param id
	 * @param name
	 * @param job
	 * @return
	 */
	@PostMapping("/modify")
	def modify(String id, String name, String job, Map map){
		Tb_Employee employee = employeeService.findById(id)
		employee.setId(id)
		employee.setName(name)
		employee.setJob(job)
		employee.setModifyuserid("20112004")
		employee.setNewRecord(false)
		employeeService.save(employee)
		def total = employeeService.recordCntByTerm("");
		map.put("total", total)
		return URL + "/list"
	}
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@GetMapping("/move")
	def move(String id, Map map){
		println "id is : $id"
		def ids = id.split(",")
		Integer i = employeeService.delete(ids)
		def total = employeeService.recordCntByTerm("");
		map.put("total", total)
		return URL + "/list"
	}
	/**
	 * 执行删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping("/delete/{id}")
	def delete(@PathVariable("id") String id){
		def ids = id.split(",")
		Integer i = employeeService.delete(ids)
		return ServerResultJson.success("Deleted $i records!")
	}
	/**
	 * 执行数据获取-全部数据分页
	 * @param employee
	 * @param order
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@GetMapping("/all")
	def all(String order, Integer page, Integer limit, String name){
		def countByTerm = employeeService.recordCntByTerm(name);
		Page<Tb_Employee> allByTerm = employeeService.findAllByTerm(order, page, limit, name)
		return ServerResultJson.success(allByTerm, countByTerm)
	}
	
}