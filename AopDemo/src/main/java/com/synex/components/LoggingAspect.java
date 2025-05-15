package com.synex.components;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // This method will be executed before any method in the Service class.
    @Before("execution(* com.synex.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before executing method: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args) {
        	System.out.println("Passed Argument:"+arg);
        }
    }

    // This method will be executed after any method in the Service class.
    @After("execution(* com.synex.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After executing method: " + joinPoint.getSignature().getName());
        
    }
    
    // This method will be executed after any method in the Service class.
    @AfterReturning(pointcut = "execution(* com.synex.services.*.*(..))",returning = "result")
    public void logAfterreturning(JoinPoint joinPoint,Object result) {
        System.out.println("After executing method: " + joinPoint.getSignature().getName());
        System.out.println("Return Value: " + result);
    }
}
