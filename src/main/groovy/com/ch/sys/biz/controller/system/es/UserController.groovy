package com.ch.sys.biz.controller.system.es
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.ch.sys.biz.dao.es.entity.EsUser
import com.ch.sys.biz.system.results.ServerResultJson
import com.fasterxml.jackson.databind.ObjectMapper

import java.net.Authenticator.RequestorType

import javax.servlet.http.HttpServletRequest

import org.elasticsearch.action.delete.DeleteRequest
import org.elasticsearch.action.delete.DeleteResponse
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
@RestController
@RequestMapping("/es/user")
class UserController {
	@Autowired
	RestHighLevelClient restHighLevelClient

	@PostMapping("/add")
	def add() {
		//以下使用json字符串进行存储
		EsUser user = new EsUser()
		user.setName("ESUserFromSpring")
		user.setSex(1)
		user.setAge(28)
		user.setBook("ElasticSearch 入门到精通")
		user.setRemark("数据来至于SpringBoot客户端")
		ObjectMapper mapper = new ObjectMapper()
		String jsonStr = mapper.writeValueAsString(user)
		IndexRequest request = new IndexRequest("doosan_db", "user", UUID.randomUUID().toString()).source(jsonStr, XContentType.JSON)
		restHighLevelClient.index(request, RequestOptions.DEFAULT)
		return "SUCCESS"
	}
	
	@PutMapping("/update")
	def update(HttpServletRequest request) {
		GetRequest getRequest = new GetRequest("doosan_db", "user", request.getParameter("id").toString())
		GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT)
		def data = response.getSourceAsMap()
		EsUser user = new EsUser(data.get("name"), data.get("sex"), data.get("age"), data.get("book"), data.get("remark"))
		user.setName(request.getParameter("name"))
		user.setSex(Integer.parseInt(request.getParameter("sex")))
		user.setAge(Integer.parseInt(request.getParameter("age")))
		user.setBook(request.getParameter("book"))
		user.setRemark(request.getParameter("remark"))
		ObjectMapper mapper = new ObjectMapper()
		String jsonStr = mapper.writeValueAsString(user)
		UpdateRequest updateRequest = new UpdateRequest("doosan_db", "user", request.getParameter("id").toString()).doc(jsonStr, XContentType.JSON)
		restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT)
		return ServerResultJson.success("Update Successfully!")
	}
	
	@DeleteMapping("/delete/{id}")
	def delete(@PathVariable String id) {
		DeleteRequest request = new DeleteRequest("doosan_db", "user", id)
		restHighLevelClient.delete(request, RequestOptions.DEFAULT)
		return ServerResultJson.success("Delete Successfully!")
	}
	
	
	@GetMapping("/search/{id}")
	def getById(@PathVariable("id") String id) {
		GetRequest request = new GetRequest("doosan_db", "user", id)
		GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT)
		def data = response.getSourceAsMap()
		data.each { k, v ->
			println "Key is : $k, Value is : $v"
		}
		EsUser user = new EsUser(data.get("name"), data.get("sex"), data.get("age"), data.get("book"), data.get("remark"))
		return ServerResultJson.success(user)
	}
//	
//	@GetMapping("/search/{page}/{size}/{q}")
//	Page<Book> getByPages(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q){
//		BoolQueryBuilder builder = QueryBuilders.boolQuery()
//		builder.must(QueryBuilders.matchQuery("message", q))
//		Page<Book> pageList = bookService.search(builder, new PageRequest(page, size))
//		return pageList
//	}
	
}