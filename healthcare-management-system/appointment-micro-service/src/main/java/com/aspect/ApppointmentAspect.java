package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.JoinPointSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApppointmentAspect {

	@Around("execution(* com.service.AppointmentService.*(..))")
	public Object logAppointment(ProceedingJoinPoint joinPoint) throws Throwable
	{
        long start = System.currentTimeMillis();
        
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        
        return proceed;	
        }
}
