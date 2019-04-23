package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public Object exception(NullPointerException e) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", 400);
		params.put("msg", e.getMessage());
		return params;
	}
	
	// 一个异常不能用两个@ExceptionHandler
//	@ExceptionHandler({NullPointerException.class, MyException.class})
//	public Object otherException(Exception e) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("code", 400);
//		params.put("msg", e.getMessage());
//		return params;
//	}
	
	@ExceptionHandler(MyException.class)
	public Object myException(MyException e) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", e.getCode());
		params.put("msg", e.getMsg());
		return params;
	}
	
}
