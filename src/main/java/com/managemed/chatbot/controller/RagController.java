package com.managemed.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.managemed.chatbot.service.RagService;

@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/")
    public ResponseEntity<String> generate(@RequestBody String message) {
        System.out.print("Received message: " + message);
        String response =  ragService.retrieveAndGenerate(message);
        // System.out.println("Generated response: " + response);
        return ResponseEntity.ok(response);
    }
}
