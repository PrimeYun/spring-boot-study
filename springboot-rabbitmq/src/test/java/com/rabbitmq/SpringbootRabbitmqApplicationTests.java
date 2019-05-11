package com.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabbitmq.send.MQSend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private MQSend send;
	
	@Test
	public void send() {
		for (int i = 0; i < 10; i++) {
			send.send(i);
		}
	}
	
}
