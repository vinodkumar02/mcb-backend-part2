package com.mcb.part2.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mcb.part2.utils.CommonUtil;

import lombok.extern.slf4j.Slf4j;



@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Autowired
	Environment environment;
	
	@Autowired
	HttpServletRequest request;
	
	
	@Autowired
	CommonUtil commonUtil;
		
	@Around("execution(public * com.mcb.part2.controller..*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		MDC.put("startTime",startTime.toString());
		MDC.put("methodName", methodName);
		MDC.put("className", className);
		MDC.put("remoteAddr", request.getRemoteAddr());
		MDC.put("requestContent", commonUtil.objecToJson(joinPoint.getArgs()));
		MDC.put("remoteAddr", request.getRemoteAddr());
		MDC.put("requestURI", request.getRequestURI());
		if(request.getSession()!=null)
		{
			MDC.put("sessionId", request.getSession().getId());
		}	
		log.info("Requst");
		MDC.remove("requestContent");
		Object result = joinPoint.proceed();
		Long endTime = System.currentTimeMillis() - startTime;
		if(result instanceof ResponseEntity)
		{
			@SuppressWarnings("rawtypes")
			ResponseEntity ob=(ResponseEntity)result;
			MDC.put("responseContent", ob.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
		}
		else
		{
			
			MDC.put("responseContent", commonUtil.objecToJson(result));
		}	
		MDC.put("processingTime", endTime.toString());
		log.info("Response");
		MDC.clear();
		return result;
	}
	
	
	@Around("execution(public * com.mcb.part2.service..*.*(..)) || execution(public * com.mcb.part2.repository..*.*(..))")
	public Object logAroundSErvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
	
		MDC.put("methodName", methodName);
		MDC.put("className", className);
		log.info("Requst");
		
		Object result = joinPoint.proceed();
		Long endTime = System.currentTimeMillis() - startTime;
		MDC.put("processingTime", endTime.toString());
		log.info("Response");
		return result;
	}
	
	@AfterThrowing(pointcut = "execution(public * com.oasys.commisionmanagement.controller..*.*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinpoint, Exception ex) {
		MDC.put("methodName", joinpoint.getSignature().getName());
		MDC.put("className", joinpoint.getTarget().getClass().getName());
		log.error(ex.getMessage(), ex);
	}
	
	@AfterReturning(pointcut="execution(public * com.oasys.commisionmanagement.config.ExceptionControllerAdvice..*(..))",returning="result")
	public Object logAroundAdvice(JoinPoint joinPoint,Object result) throws Throwable {
		
		if(MDC.get("startTime")!=null)
		{
			Long endTime = System.currentTimeMillis() - Long.parseLong(MDC.get("startTime"));
			MDC.put("processingTime", endTime.toString());
		}	
		MDC.put("methodName", joinPoint.getSignature().getName());
		MDC.put("className", joinPoint.getTarget().getClass().getName());
		MDC.put("responseContent", commonUtil.objecToJson(result));
		log.info("Response");
		MDC.clear();
		return result;
	}

}
