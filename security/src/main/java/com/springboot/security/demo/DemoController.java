package com.springboot.security.demo;


import com.springboot.security.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping
    public ResponseEntity<?> demo(){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  ResponseEntity.ok(UserResponse.builder().email(userDetails.getUsername()).build());
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return  ResponseEntity.ok("Admin Endpoint");
    }

}
