package com.ch.sys.biz.controller.system.es
//import org.elasticsearch.index.query.BoolQueryBuilder
//import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.domain.PageRequest
//import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.ch.sys.biz.service.es.ElasticSearchService
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
//import com.ch.sys.biz.service.es.BookService
//import com.ch.sys.biz.system.es.entity.Book
//import com.github.pagehelper.Page
@RestController
@RequestMapping("/es/user")
class UserController {
	@Autowired
	RestHighLevelClient restHighLevelClient;
	@Autowired
	ElasticSearchService elasticSearchService;
	
	@PostMapping("/add")
	def add() {
		Map data = new HashMap()
		data.put("name", "SpringBootClient")
		data.put("sex", 0)
		data.put("age", 23)
		data.put("book", "Spring入门到精通")
		data.put("remark", "this is a data from spring boot")
		elasticSearchService.add("doosan_db", "user", "qqqqqqqq", data)
		return "SUCCESS"
	}
//	
//	@PostMapping("/update")
//	Book update(Book book) {
//		bookService.save(book)
//		println "Book has been insert to the ES"
//		return book
//	}
//	
//	@PostMapping("/delete/{id}")
//	Book delete(@PathVariable String id) {
//		Optional<Book> op = bookService.findOne(id)
//		bookService.delete(op.get())
//		return op.get()
//	}
//	
//	@GetMapping("/search/{id}")
//	Optional<Book> getById(@PathVariable String id){
//		return bookService.findOne(id)
//	}
	
	@GetMapping("/search/{id}")
	def getIndexTest(@PathVariable("id") String id) {
		GetRequest request = new GetRequest("doosan_db", "user", id)
		GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT)
		println "Index is : " + response.getIndex()
		println "Type is : " +  response.getType()
		def data = response.getSourceAsMap()
		return data
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