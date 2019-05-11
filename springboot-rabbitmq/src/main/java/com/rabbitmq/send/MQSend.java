package com.rabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQSend {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Integer i) {
		amqpTemplate.convertAndSend("hello", "spring boot  " + i);
	}
	
}
