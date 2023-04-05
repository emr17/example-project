## Spring IOC

In Spring Framework and Spring Boot, Inversion of Control (IoC) is implemented using the concept of beans, which are objects that can be wired together to form an application. Beans can be created, managed, and wired together using the ApplicationContext, which is a container for beans.

One way to create beans in Spring is through the use of component scanning. Component scanning is a mechanism that allows the framework to automatically detect and instantiate beans based on certain criteria, such as annotations, classpath location, or naming conventions. This eliminates the need to manually define every bean in configuration files, and helps reduce boilerplate code.

To enable component scanning in a Spring application, the @ComponentScan annotation can be used. This annotation is typically placed on the main application class and specifies the packages to be scanned. Once the application context is created, the framework will scan these packages and automatically register any beans that meet the criteria.

@ComponentScan is already included in @SpringBootApplication annotation :

<img alt="img.png" height="200" src="img.png" width="400"/>

The ApplicationContext also supports the creation of hierarchical contexts, which can be used to manage the lifecycle of related beans. This allows for more fine-grained control over bean creation and management.

We can get a bean by using applicationContext in our application:

```
@Autowired
private ApplicationContext applicationContext;

MyBean myBean = applicationContext.getBean(MyBean.class);

```

In Spring Boot, the ApplicationContext is automatically created and configured based on the application's configuration files and annotations.