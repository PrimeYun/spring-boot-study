package com.example.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.annotation.Example;

@Aspect
@Component
public class ExampleAspect {
	
	private static final Logger log = LoggerFactory.getLogger(ExampleAspect.class);
	
	@Pointcut("@annotation(com.example.annotation.Example)")
	public void pointcut() {
		
	}
	
	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Example test = method.getAnnotation(Example.class);
		log.info("Example注解内容为：" + test.value());
	}
	
	@AfterReturning(returning = "obj", pointcut = "pointcut()")
	public void doAfter(Object obj) {
		log.info("方法执行成功后返回结果为：" + obj.toString());
	}
	
}
