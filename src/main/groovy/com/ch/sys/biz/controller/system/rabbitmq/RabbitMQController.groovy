package com.ch.sys.biz.controller.system.rabbitmq
import com.ch.sys.biz.system.configuration.rabbitmq.RabbitMQFanoutConfig
import com.ch.sys.biz.system.queue.producer.FanoutProducer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/rabbit")
class RabbitMQController {
	
	@Autowired
	FanoutProducer fanoutProducer;
	
	@GetMapping("/queue/email")
	def emailQueue() {
		def message = "This is the email queue. now : " + new Date()
		fanoutProducer.sendRabbitMQ(RabbitMQFanoutConfig.QUEUE_EMAIL, message)
		return "success"
	}
	
	@GetMapping("/queue/sms")
	def smsQueue() {
		def message = "This is the sms queue. now : " + new Date()
		fanoutProducer.sendRabbitMQ(RabbitMQFanoutConfig.QUEUE_SMS, message)
		return "success"
	}
}
