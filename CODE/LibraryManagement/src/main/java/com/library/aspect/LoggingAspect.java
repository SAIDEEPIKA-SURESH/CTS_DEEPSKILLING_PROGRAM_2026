package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.*.*(..))")
    public void beforeMethod() {
        System.out.println("Before Method Execution");
    }

    @After("execution(* com.library.service.*.*(..))")
    public void afterMethod() {
        System.out.println("After Method Execution");
    }

    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                joinPoint.getSignature().getName()
                        + " executed in "
                        + (end - start)
                        + " ms");

        return result;
    }
}
