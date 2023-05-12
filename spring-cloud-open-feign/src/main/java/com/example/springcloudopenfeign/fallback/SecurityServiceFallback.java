package com.example.springcloudopenfeign.fallback;


import com.example.springcloudopenfeign.dto.request.AuthenticationRequest;
import com.example.springcloudopenfeign.dto.request.RegisterRequest;
import com.example.springcloudopenfeign.dto.response.AuthenticationResponse;
import com.example.springcloudopenfeign.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
