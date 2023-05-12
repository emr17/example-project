package com.springboot.security.auth;

import com.springboot.security.user.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;

    private String lastname;

    @NonNull
    private String email;

    @NonNull
    private String password;



}
