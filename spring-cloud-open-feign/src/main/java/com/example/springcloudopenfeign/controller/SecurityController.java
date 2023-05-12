package com.example.springcloudopenfeign.controller;


import com.example.springcloudopenfeign.dto.request.AuthenticationRequest;
import com.example.springcloudopenfeign.dto.request.RegisterRequest;
import com.example.springcloudopenfeign.dto.response.AuthenticationResponse;
import com.example.springcloudopenfeign.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/register")
    private AuthenticationResponse register(@RequestBody RegisterRequest registerRequest){
        return securityService.register(registerRequest);
    }

    @PostMapping("/authenticate")
    private AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return securityService.authenticate(authenticationRequest);
    }

    @GetMapping("/demo")
    public ResponseEntity<?> demo(){
        return securityService.demo();
    }




}
