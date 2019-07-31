package com.ch.sys.biz.system.queue.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class FanoutProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendRabbitMQ(String queue, String message) {
		System.out.println("Queue name is : " + queue + ", message is : " + message);
		rabbitTemplate.convertAndSend(queue, message);
	}
}