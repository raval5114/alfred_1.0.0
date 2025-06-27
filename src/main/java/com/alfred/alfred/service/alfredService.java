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

  private final String apiKey;
  private final String apiUrl;
  private final RestTemplate restTemplate = new RestTemplate();
  private final ObjectMapper mapper = new ObjectMapper();

  public alfredService() {
    String key = System.getenv("GROQ_API_KEY");
    String url = System.getenv("GROQ_API_URL");

    if (key == null || url == null) {
      try {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        if (key == null)
          key = dotenv.get("GROQ_API_KEY");
        if (url == null)
          url = dotenv.get("GROQ_API_URL");
      } catch (Exception e) {
        throw new IllegalStateException("Missing environment variables and failed to load .env file", e);
      }
    }

    if (key == null || url == null) {
      throw new IllegalStateException("GROQ_API_KEY or GROQ_API_URL not set in environment or .env");
    }

    this.apiKey = key;
    this.apiUrl = url;
  }

  public String chat(String prompt) {
    try {
      Map<String, Object> payload = new HashMap<>();
      payload.put("model", "llama3-70b-8192");
      payload.put("messages", List.of(Map.of("role", "user", "content", prompt)));
      payload.put("temperature", 0.7);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setBearerAuth(apiKey);

      HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(payload), headers);

      ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

      JsonNode root = mapper.readTree(response.getBody());
      JsonNode contentNode = root.path("choices").get(0).path("message").path("content");

      return contentNode.asText();

    } catch (Exception e) {
      e.printStackTrace();
      return "⚠️ Alfred encountered an issue: " + e.getMessage();
    }
  }
}
