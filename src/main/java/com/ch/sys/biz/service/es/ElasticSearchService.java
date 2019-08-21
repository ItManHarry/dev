package com.ch.sys.biz.service.es;
import java.util.Map;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Service;
@Service
public class ElasticSearchService {
	
	public void add(String index, String type, String id, Map<String, Object> data) {
		System.out.println("Index is : " + index + ", type is : " + type + ", Id is : " + id);
		IndexRequest request = new IndexRequest(index, type, id).source(data);
	}
}
