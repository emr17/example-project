package com.example.helloworld;


import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    //
    @Before("execution(* com.example.helloworld.worker.PutProductWorker.execute(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("Caught a parameter before execution of method " + joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getSignature());
    }


    @AfterReturning(value = "execution(* com.example.helloworld.worker.PutProductWorker.execute(..))", returning = "taskResult")
    public void afterReturningMethod(JoinPoint joinPoint, TaskResult taskResult) {
        System.out.println("The method is completed. Returned: " + taskResult.toString());
        System.out.println(joinPoint.getSignature());
    }


    @After("execution(* com.example.helloworld.worker.PutProductWorker.execute(..))")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("The method is completed");
    }


    @Around("execution(* com.example.helloworld.worker.PutProductWorker.execute(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Task task = (Task) joinPoint.getArgs()[0];
        List<String> products = (List<String>) task.getInputData().get("products");
        if (products.stream().filter(product -> "bread".equals(product)).count() == 2)
            return null;

        Object object = joinPoint.proceed();
        System.out.println("Return of around method: " + object.toString());

        return object;
    }
}