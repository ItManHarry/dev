package com.ch.sys.biz.system.queue.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.ch.sys.biz.system.configuration.rabbitmq.RabbitMQFanoutConfig;
//@Component
//@RabbitListener(queues = RabbitMQFanoutConfig.QUEUE_SMS)
public class SmsQueueConsumer {

//	@RabbitHandler
	public void doSmsConsumer(String message) {
		System.out.println("==============>SMS Queue Has Been Consumed. Message is : " + message);
	}
}
