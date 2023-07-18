package com.sb_kh.week1.aspect;

import com.sb_kh.week1.exception.NetworkErrorException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryMethod {

    @Pointcut("@annotation(com.sb_kh.week1.aspect.annotations.Retry) || @within(com.sb_kh.week1.aspect.annotations.Retry) ")
    public void retryPointcut(){}

    @Around("com.sb_kh.week1.aspect.RetryMethod.retryPointcut()")
    public Object retryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Retry AOP started...");

        for (int i = 0; i < 5; i++) {
            try {
                return joinPoint.proceed();
            } catch (NetworkErrorException e) {
                Thread.sleep(500l);
                System.out.println("Retry calling by AOP...");
            }
        }

        System.out.println("Retry AOP stopped...");
        throw new RuntimeException("Retry failed...");
    }

}
