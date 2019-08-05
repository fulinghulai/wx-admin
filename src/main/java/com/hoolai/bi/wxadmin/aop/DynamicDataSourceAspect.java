package com.hoolai.bi.wxadmin.aop;

import java.lang.reflect.Method;

import com.hoolai.bi.wxadmin.conf.DynamicDataSourceHolder;
import com.hoolai.bi.wxadmin.entity.TargetDataSource;
import org.apache.ibatis.binding.MapperMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-01 20:52
 * Description:
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	@Pointcut("@annotation(com.hoolai.bi.wxadmin.entity.TargetDataSource)")
	public void point() {
	}

	@Around(value = "point()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method targetMethod = methodSignature.getMethod();
		if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
			String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
			DynamicDataSourceHolder.setDataSource(targetDataSource);
		}
		Object result = pjp.proceed();// 执行方法
		DynamicDataSourceHolder.clearDataSource();
		return result;
	}

}
