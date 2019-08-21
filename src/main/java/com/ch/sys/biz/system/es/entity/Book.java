package com.ch.sys.biz.system.es.entity;
import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//@Document(indexName = "doosan_db", type = "book")
public class Book {

	@Id
	private String id;	
	private String name;	
	private String message;	
	private String type ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}