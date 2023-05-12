package com.example.springcloudopenfeign.service;


import com.example.springcloudopenfeign.config.SecurityServiceConfig;
import com.example.springcloudopenfeign.dto.request.AuthenticationRequest;
import com.example.springcloudopenfeign.dto.request.RegisterRequest;
import com.example.springcloudopenfeign.dto.response.AuthenticationResponse;
import com.example.springcloudopenfeign.fallback.SecurityServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
