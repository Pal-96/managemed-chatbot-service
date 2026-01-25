package com.managemed.chatbot.controller;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class OpenAIController {
    @Autowired
    private OpenAiChatModel chatModel;

    public OpenAIController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/")
    public ResponseEntity<String> getResponse(@RequestBody String message) {
        // String response = chatModel.call(message);
        // return ResponseEntity.ok(message);
        
        return ResponseEntity.ok("Thanks for the message:" + message + "Hello from OpenAIController");
    }
}
