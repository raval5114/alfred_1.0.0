package com.alfred.alfred.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class alfredService {

  Dotenv dotenv = Dotenv.load();
  String apiKey = dotenv.get("GROQ_API_KEY");
  String apiUrl = dotenv.get("GROQ_API_URL");

  private final RestTemplate restTemplate = new RestTemplate();
  private final ObjectMapper mapper = new ObjectMapper();

  public String chat(String prompt) {
    try {
      // Construct the message payload
      Map<String, Object> payload = new HashMap<>();
      payload.put("model", "llama3-70b-8192");
      payload.put("messages", List.of(
          Map.of("role", "user", "content", prompt)));
      payload.put("temperature", 0.7);

      // Set headers
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setBearerAuth(apiKey);

      // Create request
      HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(payload), headers);

      // Send request
      ResponseEntity<String> response = restTemplate.exchange(
          apiUrl, HttpMethod.POST, request, String.class);

      // Extract message
      JsonNode root = mapper.readTree(response.getBody());
      JsonNode contentNode = root.path("choices").get(0).path("message").path("content");

      return contentNode.asText();

    } catch (Exception e) {
      e.printStackTrace();
      return "⚠️ Alfred encountered an issue: " + e.getMessage();
    }
  }
}
