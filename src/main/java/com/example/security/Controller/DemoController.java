package com.example.security.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {


    @GetMapping
   // @PreAuthorize("hasRole('ADMIN')") Don't work
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World!");
    }

}
