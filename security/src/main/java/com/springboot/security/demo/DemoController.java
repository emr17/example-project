package com.springboot.security.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping
    public ResponseEntity<?> demo(){
        return  ResponseEntity.ok("Secured Endpoint");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return  ResponseEntity.ok("Secured Endpoint");
    }

}
