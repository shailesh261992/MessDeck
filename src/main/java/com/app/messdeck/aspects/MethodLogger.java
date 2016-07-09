package com.app.messdeck.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLogger {
	private static Logger logger = Logger.getLogger(MethodLogger.class);

	{
		System.out.println("MethodLogger object called");
	}

	@Around("@annotation(com.app.messdeck.annotations.Loggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		logger.info("Method called : " + point.getSignature());
		logger.info("Method Arguments : " + Arrays.asList(point.getArgs()));
		Object result = point.proceed();
		logger.info("Method Return : " + result);
		logger.info("ExecutionTime : " + (System.currentTimeMillis() - start) + " miliiSecond");

		return result;
	}
}