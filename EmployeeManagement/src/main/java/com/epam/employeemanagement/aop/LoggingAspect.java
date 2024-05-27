package com.epam.employeemanagement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/*
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
*/
@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Around("execution(* com.epam.employeemanagement.*.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("Entered method: {} in class: {} with {}", methodName, className, args);

        Object result;
        try {
            // Proceed with the original method execution
            result = joinPoint.proceed();
            log.info("Exiting method: {} in class: {} with {}", methodName, className, result);
        } finally {
            Instant endTime = Instant.now();
            long executionTime = Duration.between(startTime, endTime).toMillis();
            log.info("Method: {} in class: {} took {} milliseconds to execute", methodName, className, executionTime);
        }

        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.epam.employeemanagement.*.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.error("Exception in method: {} in class: {} due to {}", methodName, className, exception.getMessage());
    }
}
