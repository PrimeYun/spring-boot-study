package com.example;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void redisTest() {
		redisTemplate.opsForValue().set("redisTest", "RedisTest");
	}
	
	@Test
	public void userTest() {
		User user = new User();
		user.setName("SpringBoot");
		user.setAge(1);
		redisTemplate.opsForValue().set("user", user);
	}
	
}
