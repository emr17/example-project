## Spring AOP
Spring AOP can be used to define common behaviors at different points in the application, reducing code repetition, increasing modularity, and improving code readability. Additionally, many features of the Spring Framework, such as transaction management, event management, security management, caching, and performance monitoring, are implemented using Spring AOP.

Spring AOP is used to apply specific behaviors at join points. A Joinpoint is a point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a JoinPoint always represents a method execution.

A Pointcut is a predicate that helps match an Advice to be applied by an Aspect at a particular JoinPoint.

We often associate the Advice with a Pointcut expression, and it runs at any Joinpoint matched by the Pointcut.

Spring AOP determines how to handle join points using proxy objects.

Spring AOP accesses objects through proxy (delegate) objects. Proxy objects are used in place of real objects and control object management. 

Since proxy objects are used by aspects, there is no need to modify real objects. This allows aspects to be applied during application runtime without making any changes.

Advices define how an aspect should apply specific behaviors at join points. Spring AOP supports various types of advice, which are defined using annotations such as @Before, @After, @Around, @AfterReturning, @AfterThrowing, and @DeclareParents. For example, the @Transactional annotation uses @Around advice to manage transactions.



#### Usage

Firstly add to the main class (For AOP to handle classes that do not implement interfaces, the proxyTargetClass must be set to true.):
```
@EnableAspectJAutoProxy(proxyTargetClass = true)
```

Then create a aspect class. Add `` @Aspect `` and  ``@Component`` annotations to that class.

In this project, an Aspect class (com.example.helloworld.Aspect) has been created as an example.

#### Example usage of @Before advice:

In the Before advice, we specify which method or methods we will handle with the "execution(* com.example.helloworld.worker.PutProductWorker.execute(..))" pointcut query.

We write the code that we want to run before the execute method in the PutProductWorker class inside the method. We can access the parameter of the listened method with the Joinpoint object.
```
    @Before("execution(* com.example.helloworld.worker.PutProductWorker.execute(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("Caught a parameter before execution of method " + joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getSignature());
    }

```





