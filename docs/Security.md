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

## Glossary

JWT: A data format used to provide authentication for users over the internet. JWTs are used to carry authentication credentials (e.g., username, password, etc.) during data transfer. JWT consists of 3 parts: header, payload, and signature. The header specifies the type of token and the algorithm used. The payload contains the data required for user authentication. The signature is signed with a key used to verify the token's validity.

Authentication: The process of checking the user's information who wants to access the application.

Authorization: If the authentication process is successful, the process of checking the user's authorities (roles) and granting or denying access to the requested endpoint.

Claim: Parameters found in the payload part of JWT. When creating a token, in addition to claims like subject and exp (expiration date), extra claims can also be added if desired.

Spring Security needs user information during the authentication process. It uses the UserDetails and UserDetailsService interfaces to provide and manage this information. In this way, you can customize the authentication process according to your application's user model and storage method.

UserDetails: This is a user representation used by Spring Security. It contains the basic information about the user during the authentication process, particularly providing information such as username, password, and authorizations (roles and permissions).

UserDetailsService: This is a service used to load user details. It contains a single method called loadUserByUsername, which returns a UserDetails object based on the username. This service can be used to load users from a database or an external source.

Responsibility Chain Pattern: A design pattern that provides a structure where a series of objects direct and perform operations or requests among themselves. Spring Security manages security filters using this pattern.

Spring Security filter chain: Contains a series of filters that run during request processing. The request is passed to each filter in turn, and each filter performs a customized operation. When the operation is complete, the request moves to the next filter in the chain.

Basic Authentication: Performing authentication and authorization operations with user username and password information. In this case, a token is not used, and every request must contain the username and password information. No additional filter is needed; the default filters in the Spring Security filter chain are sufficient.

SecurityContext: A thread-local object. A thread can only read the value it sets. If the authentication process is successful, it is updated with the user's information. It is cleared when the request is completed.

JwtAuthenticationFilter: The filter we add to the security filter chain to implement JWT. In this filter, the token is checked, controls are made according to the subject in the token, and if successful, the user information is processed in the SecurityContext. This filter is added to the security filter chain and runs on all requests except allowed endpoints such as login and register.

UsernamePasswordAuthenticationToken: A basic authentication object used in Spring Security. This class is used to create an authentication object containing the username and password information. It is also added to the SecurityContext after the authentication processes.

The main properties of the UsernamePasswordAuthenticationToken class are:

Username (principal): A unique value identifying the user, typically a value such as a username or email address. We can set user object as a principal too.
Password (credentials): The user's password. The password is checked during the authentication process.
Authorities (authorities): A collection of GrantedAuthority objects representing the user's authorities. Authorities determine the features and capabilities that users can access within the application.
Logout Handler: An interface where the operations to be performed during logout in Spring Security are located. By implementing this interface in a custom class, you can set the operations to be performed during logout.

Revoked: A boolean value indicating whether the token stored in the database has been canceled or not. When a user logs in, if they have a token in the database that is not revoked, the revoked value is set to true. Also, when a user logs out, if they have a non-revoked token in the database, the revoked value is set to true.

