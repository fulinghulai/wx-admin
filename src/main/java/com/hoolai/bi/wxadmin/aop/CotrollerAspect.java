package com.hoolai.bi.wxadmin.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CotrollerAspect {

	@Pointcut("execution( public java.util.Map com.hoolai.bi.wxadmin.controller.*.*(..))")
	public void aop() {

	}

	@Around("aop()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, Object> result = new HashMap<>();
            try {
                result.putAll((Map<String, Object>) joinPoint.proceed());
                return result;
            } catch (Throwable e) {
                e.printStackTrace();
                result.put("success", false);
			result.put("msg", e.getMessage());
		}

		return result;
	}
}
