package com.epam.genrativeaiapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {


    @Before("execution(* com.epam.employeemanagement.*.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("Entered method: {} in class: {} with {}", methodName, className, args);
    }

    @AfterReturning(pointcut = "execution(* com.epam.employeemanagement.*.*.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("Exiting method: {} in class: {} with {}", methodName, className, result);
    }

    @AfterThrowing(pointcut = "execution(* com.epam.employeemanagement.*.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.error("Exception in method: {} in class: {} due to {}", methodName, className, exception.getMessage());
    }
}
