package com.cjk.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	@Pointcut("execution(public * com.cjk.controller.IndexController.*(..))")
	public void log() {
	}
	
	 
	@Before("log()")     //避免代码重复，“可为execution(public * com.cjk.controller.IndexController.*(..))”
	public void dobefore(JoinPoint joinpoint) {
		//url method ip 类方法 参数  进行记录
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 HttpServletRequest request = attributes.getRequest();
		 
		 logger.info("url = {}",request.getRequestURL()); //url
		 
		 logger.info("method = {}", request.getMethod());
		 
		 logger.info("ip = {}", request.getRemoteAddr());
		 //类方法
		 logger.info("class_method = {}", joinpoint.getSignature().getDeclaringTypeName() +"." + joinpoint.getSignature().getName());
		 //参数
		 logger.info("args = {}", joinpoint.getArgs());
	}
	
	@After("log()")
	public void doAfter() {
		
	}
	
	@AfterReturning(returning = "object", pointcut = "log()")
	public void getReturning(Object object) {
		logger.info("response = {}", object.toString());
	}
}
