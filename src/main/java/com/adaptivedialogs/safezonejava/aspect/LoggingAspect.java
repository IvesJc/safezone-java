package com.adaptivedialogs.safezonejava.aspect;


import com.adaptivedialogs.safezonejava.usecases.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogService logService;

    @AfterReturning("within(@org.springframework.stereotype.Controller *)")
    public void logAfterControllerMethods(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        String message = String.format("Método %s.%s chamado com argumentos %s", className, methodName, args);
        logService.log(message, "INFO", className);
    }

    @AfterThrowing(pointcut = "within(@org.springframework.stereotype.Controller *)", throwing = "ex")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        String message = String.format("Erro no método %s.%s com argumentos %s. Exceção: %s", className, methodName, args, ex.getMessage());
        logService.log(message, "ERROR", className);
    }

}