// package com.alfred.alfred.controller;

// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.alfred.alfred.models.dto.requestBody;
// import com.alfred.alfred.service.alfredService;

// @RestController
// @RequestMapping("/alfred")
// public class AlfredController {
// private final alfredService service;

// AlfredController(alfredService service) {
// this.service = service;
// }

// @GetMapping("/testing")
// String testing() {
// return "Yes its hariraval";
// }

// @PostMapping("/talk")
// ResponseEntity<Map<String, String>> postAnswer(@RequestBody requestBody
// request) {
// try {
// String reply = service.chat(request.getMessage());
// return ResponseEntity.ok(Map.of("reply", reply));
// } catch (Exception e) {
// e.printStackTrace();
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// .body(Map.of("error", "Failed to get response from OpenAI" +
// e.getMessage()));
// }

// }
// }
