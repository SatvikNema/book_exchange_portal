package com.satvik.bookexchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck(){
        String response = "Backend APIs are up";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
