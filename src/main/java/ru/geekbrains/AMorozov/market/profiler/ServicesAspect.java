package ru.geekbrains.AMorozov.market.profiler;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
@RequiredArgsConstructor
@Deprecated
public class ServicesAspect {

    private final TimeKeeper timeKeeper;

    @Around("execution(public * ru.geekbrains.AMorozov.market.services.*.*(..))")
    private Object classProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        timeKeeper.addTime(key, duration);
        return out;
    }
}
