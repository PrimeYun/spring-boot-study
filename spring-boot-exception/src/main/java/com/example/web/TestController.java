package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.MyException;

@RestController
public class TestController {
	
	@GetMapping("null")
	public Object nullPoint(@PathVariable("id") String id) {
		throw new NullPointerException("空指针异常");		
	}
	
	@GetMapping("my")
	public Object myException() {
		throw new MyException(400, "自定义异常");
	}
	
}
