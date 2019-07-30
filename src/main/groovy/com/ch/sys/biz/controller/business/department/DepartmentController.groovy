package com.ch.sys.biz.controller.business.department
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.ch.sys.biz.dao.business.entity.department.Tb_Department
import com.ch.sys.biz.service.business.department.DepartmentService
import com.ch.sys.biz.system.results.ServerResultJson
import com.github.pagehelper.Page
@Controller
@RequestMapping("/biz/department")
class DepartmentController {

	final String URL = "biz/department"
	@Autowired
	DepartmentService departmentService
	/**
	 * 跳转至清单画面
	 * @return
	 */
	@GetMapping("/list")
	def list(Map map){
		def total = departmentService.recordCntByTerm("");
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
	 * @param department
	 * @return
	 */
	@PostMapping("/save")
	def save(HttpServletRequest request, Map map){
		Tb_Department department = new Tb_Department()
		department.setName(request.getParameter("name"))
		department.setCode(request.getParameter("code"))
		department.setParentid(Integer.parseInt(request.getParameter("parentid")))
		department.setCreateuserid("20112004")
		department.setNewRecord(true)
		departmentService.save(department)
		def total = departmentService.recordCntByTerm("");
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
		Tb_Department department = departmentService.findById(id)
		map.put("department", department)
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
	def modify(HttpServletRequest request, Map map){
		Tb_Department department = departmentService.findById(request.getParameter("id"))
		department.setId(request.getParameter("id"))
		department.setName(request.getParameter("name"))
		department.setCode(request.getParameter("code"))
		department.setParentid(Integer.parseInt(request.getParameter("parentid")))
		department.setModifyuserid("20112004")
		department.setNewRecord(false)
		departmentService.save(department)
		def total = departmentService.recordCntByTerm("");
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
		def ids = id.split(",")
		Integer i = departmentService.delete(ids)
		def total = departmentService.recordCntByTerm("");
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
		Integer i = departmentService.delete(ids)
		return ServerResultJson.success("Deleted $i records!")
	}
	/**
	 * 执行数据获取-全部数据分页
	 * @param department
	 * @param order
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@GetMapping("/all")
	def all(String order, Integer page, Integer limit, String name){
		def countByTerm = departmentService.recordCntByTerm(name);
		Page<Tb_Department> allByTerm = departmentService.findAllByTerm(order, page, limit, name)
		return ServerResultJson.success(allByTerm, countByTerm)
	}
	
}