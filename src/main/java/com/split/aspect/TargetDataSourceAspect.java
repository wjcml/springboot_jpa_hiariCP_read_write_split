package com.split.aspect;

import com.split.annotation.TargetDataSource;
import com.split.config.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TargetDataSourceAspect {
	
	/**
     * aop切面，所有施加@targetDataSource注解的方法都会通过该方法
	 * @param proceedingJoinPoint proceedingJoinPoint
	 * @param targetDataSource @targetDataSource注解中传入的参数
	 * @return
     */
	@Around("@annotation(targetDataSource)")
	public Object around(ProceedingJoinPoint proceedingJoinPoint, TargetDataSource targetDataSource) {
		try {
			DynamicDataSourceHolder.setDataSource(targetDataSource.value());
			Object result = proceedingJoinPoint.proceed();

			return result;
		} catch (Throwable e) {
			return null;
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
	}
}
