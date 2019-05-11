package com.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class MQReceiver2 {
	
	@RabbitHandler
	public void receiver(String hello) {
		System.out.println("Receiver2:" + hello);
	}
	
}
