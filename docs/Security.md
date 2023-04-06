## Spring Security with Jwt

Structure of a Jwt token:

<img alt="img_1.png" height="360" src="img_1.png" width="640"/>

The Header section specifies the algorithm used to encrypt the token.

The Payload section contains information such as the subject, creation time, and expiration time of the token. The subject identifies the user on whose behalf the token was generated and should be a unique value such as a username or email. Other parameters can also be added to the payload during token creation.

The Signature section contains a signature used for verifying the authenticity of the token. The server checks if it was produced by itself during token generation.

How Jwt security works:

<img alt="img.png" height="360" src="img.png" width="640"/>

For each request, the JwtAuthenticationFilter we created runs first.

This filter first checks if there is a token in the Authorization header of the request. If not, a "missing jwt" response is returned.

If a token is present, the subject (user email in this project) is extracted from the token. During extraction, the signature is checked to verify that it was produced by the server. If it was not produced by the server, a 403 error is returned.

The UserDetailsService is then called to retrieve the user associated with this subject. If the user does not exist, a "user not exist" response is returned.

If the user exists, a call is made to JwtService with the token to check if the token has expired. If it has expired, a "token not valid" response is returned.

If the token is valid, the SecurityContextHolder is updated. Then, in other code blocks running on the same thread, the authenticated user's subject can be obtained by calling SecurityContextHolder.getContext().getAuthentication().getPrincipal().

After updating, the request is automatically dispatched. The request is directed to the relevant controller via the DispatcherServlet.



Required dependencies: 
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
            <groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
		</dependency>
```

