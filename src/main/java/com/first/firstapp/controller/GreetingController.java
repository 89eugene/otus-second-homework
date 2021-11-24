package com.first.firstapp.controller;

import com.first.firstapp.controller.dto.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/health")
    public ResponseEntity greeting() {
        HealthResponse response = new HealthResponse();
        response.setStatus("OK");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
