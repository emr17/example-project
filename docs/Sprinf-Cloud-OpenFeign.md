## Spring Cloud Openfeign

In the world of microservices architecture, Spring Cloud OpenFeign plays a crucial role in simplifying HTTP API clients. Microservices often need to communicate with each other, and this inter-service communication is usually done over HTTP. Managing this communication manually can become complex and error-prone, especially when you have a large number of microservices. This is where Spring Cloud OpenFeign comes in.

To enable Feign Clients in your Spring Boot application, you need to annotate your main class (or a configuration class) with @EnableFeignClients.

```
@SpringBootApplication
@EnableFeignClients
public class SpringCloudOpenFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOpenFeignApplication.class, args);
	}

}

```

### Structure of a feign client: 

```
@FeignClient(value = "securityClient",
        url = "${security.api.url}",
        configuration = SecurityServiceConfig.class,
        fallback = SecurityServiceFallback.class)
public interface SecurityService {

    @PostMapping("/auth/register")
    AuthenticationResponse register(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/auth/authenticate")
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest);


    @GetMapping("/demo")
    public ResponseEntity<?> demo();

}

```

* value or name: The name of the service with which to create a ribbon load balancer. This is an arbitrary name which can be used to create a URL.
* url: A URL or a complete URL to the desired service.
* configuration: A custom configuration class for the feign client. You can define beans that you want to use with this particular Feign Client in this class.
* fallback: This defines a class that is a fallback when a failure occurs on the method call. This class should implement the interface annotated by @FeignClient and provide a plausible fallback response for its methods.
* The @FeignClient annotation tells Spring to generate a proxy for this interface so that it can intercept calls to the interface's methods.
* The methods in the SecurityService interface correspond to HTTP endpoints in the target service (in this case, the "securityClient" service). They are annotated with standard Spring MVC annotations like @GetMapping, @PostMapping, etc.
* The @RequestBody and @RequestHeader annotations work just like they do in Spring MVC. They indicate that a method parameter should be bound to the body of the web request, or a request header.

Spring Cloud OpenFeign does not provide the following beans by default for feign, but still looks up beans of these types from the application context to create the feign client:
```
Logger.Level
Retryer
ErrorDecoder
Request.Options
Collection<RequestInterceptor>
SetterFactory
QueryMapEncoder
Capability

```




### Structure of the SecurityServiceConfig: 

```
public class SecurityServiceConfig {


    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                if (requestAttributes != null) {
                    final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
                    template.header(HttpHeaders.AUTHORIZATION, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
                }
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();

    }
}

```



* Logger.Level feignLoggerLevel(): Feign includes a logger which you can use to output requests and responses to the console. This feignLoggerLevel bean sets the logging level for Feign's logger. The Logger.Level.FULL value means that Feign will log the full contents of HTTP requests and responses including headers and body. Note that this logging may include sensitive data and should not be used in production.

* RequestInterceptor requestInterceptor(): The purpose of a RequestInterceptor is to perform operations on the request before it is sent. In this specific case, the interceptor is adding an "Authorization" header to every request, copying it from the incoming HTTP request. This is useful in microservices architectures where you might want to propagate authentication information from one service to another.

* ErrorDecoder errorDecoder(): An ErrorDecoder is used to handle errors returned by the Feign client. The default ErrorDecoder provided by Feign does nothing special: it simply wraps the HTTP status code and error message into an exception. However, you can define your own ErrorDecoder if you need custom error handling. In this case, a custom FeignErrorDecoder is used. This ErrorDecoder could, for example, map certain HTTP status codes to custom exceptions.


### Structure of the FeignErrorDecoder:

```

public class FeignErrorDecoder  implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 400:
                return new ApiRequestException("Bad request");
            case 403:
                return new AccessDeniedException("Access denied");
            default:
                return new Exception("Library generic exception");
        }
    }
}

```
This class is implementing the ErrorDecoder interface provided by Feign. The purpose of an ErrorDecoder is to handle errors returned by the Feign client. When the Feign client receives a non-2xx HTTP response, it invokes the ErrorDecoder with the response.

Without the ErrorDecoder, Feign client would throw a FeignException for all non-2xx HTTP responses. This would result in a 500 Internal Server Error being returned for all types of errors, making it difficult to distinguish between different types of errors and to handle them appropriately.

### Structure of the SecurityServiceFallback:

```
@Component
public class SecurityServiceFallback implements SecurityService {
    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return new AuthenticationResponse("response for fallback");
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        return new AuthenticationResponse("response for fallback");

    }

    @Override
    public ResponseEntity<?> demo() {

        return  ResponseEntity.ok(new AuthenticationResponse("response for fallback"));
    }
}

```


@FeignClient annotation provides a fallback attribute which can be used to specify a fallback class. This class should implement the same interface as the Feign client and is used as a backup in case the main Feign client fails to call the remote service. The methods in the fallback class are called when the methods of the main Feign client fail.

To enable fallbacks in Feign, you need to set the spring.cloud.openfeign.circuitbreaker.enabled=true in application.properties.



If we want to create multiple feign clients with the same name or url so that they would point to the same server but each with a different custom configuration then we have to use contextId attribute of the @FeignClient in order to avoid name collision of these configuration beans.

@FeignClient(contextId = "fooClient", name = "stores", configuration = FooConfiguration.class)
public interface FooClient {
//..
}

@FeignClient(contextId = "barClient", name = "stores", configuration = BarConfiguration.class)
public interface BarClient {
//..
}