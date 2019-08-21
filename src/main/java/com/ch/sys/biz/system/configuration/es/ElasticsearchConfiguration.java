package com.ch.sys.biz.system.configuration.es;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * ElasticSearch配置类
 */
@Configuration
public class ElasticsearchConfiguration implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private RestHighLevelClient restHighLevelClient;
	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;
	
	/**
      * 控制Bean的实例化过程
      * @return
      * @throws Exception
      */
     @Override
     public RestHighLevelClient getObject() throws Exception {
         return restHighLevelClient;
     }
     /**
      * 获取接口返回的实例的class
      * @return
      */
     @Override
     public Class<?> getObjectType() {
         return RestHighLevelClient.class;
     }
 
     @Override
     public void destroy() throws Exception {
         try {
             if(null != restHighLevelClient){
                 restHighLevelClient.close();
             }
         } catch (final Exception e) {
             logger.error("Error closing ElasticSearch client: ", e);
         }
     }
 
     @Override
     public boolean isSingleton() {
         return false;
     }
 
     @Override
     public void afterPropertiesSet() throws Exception {
         restHighLevelClient = buildClient();
     }
 
     private RestHighLevelClient buildClient() {
         try {        	 
             restHighLevelClient = new RestHighLevelClient(
                     RestClient.builder(
                             new HttpHost(
                                     clusterNodes.split(":")[0],
                                     Integer.parseInt(clusterNodes.split(":")[1]),
                                     "http")));
         } catch (Exception e) {
             logger.error(e.getMessage());
         }
         return restHighLevelClient;
     }
}