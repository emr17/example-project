package com.springboot.security.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}
