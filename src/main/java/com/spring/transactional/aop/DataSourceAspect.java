package com.spring.transactional.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {
    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("datasource tracker :" +proceedingJoinPoint.getSignature().getName());
        return proceedingJoinPoint.proceed();
    }

    @Around("target(jakarta.persistence.EntityManager)")
    public Object logEntityManagerInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("entitymanager tracker :" +proceedingJoinPoint.getSignature().getName());
        return proceedingJoinPoint.proceed();
    }
}
