package com.alfred.alfred.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfred.alfred.models.dto.requestBody;
import com.alfred.alfred.models.dto.responseBody;

@RestController
@RequestMapping("/alfred")
public class alfred {
    @GetMapping("/testing")
    String testing() {
        return "Yes its hariraval";
    }

    @PostMapping("/talk")
    ResponseEntity<responseBody> postAnswer(@RequestBody requestBody request) {
        responseBody response = new responseBody("Yes its working", "Working Working");
        return ResponseEntity.ok(response);
    }
}
