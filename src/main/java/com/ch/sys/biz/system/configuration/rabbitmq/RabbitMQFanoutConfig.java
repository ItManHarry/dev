package com.ch.sys.biz.system.configuration.rabbitmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQFanoutConfig {
	
	//邮箱队列
	public static final String QUEUE_EMAIL = "fanout.queue.email";
	//短信队列
	public static final String QUEUE_SMS = "fanout.queue.sms";
	//交换机名称
	private final String EXCHANGE_NAME = "fanout.exchange";
	//定义邮件队列
	@Bean
	public Queue fanoutEmailQueue() {
		return new Queue(QUEUE_EMAIL);
	}
	//定义短信队列
	@Bean
	public Queue fanoutSmsQueue() {
		return new Queue(QUEUE_SMS);
	}
	//定义交换机
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}
	//绑定队列和交换机
	@Bean
	public Binding bindingExchangeEmail() {
		return BindingBuilder.bind(fanoutEmailQueue()).to(fanoutExchange());
	}
	@Bean
	public Binding bindingExchangeSms() {
		return BindingBuilder.bind(fanoutSmsQueue()).to(fanoutExchange());
	}
}
