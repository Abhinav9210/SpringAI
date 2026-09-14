package com.eazybytes.openai.section2.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    private final ChatClient chatClient;
    public PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-stuffing")
    public String emailResponse(@RequestParam("message")String customerMessage){
        return chatClient.prompt()
                .system(promptSystemSpec -> {
                    promptSystemSpec.text(systemPromptTemplate);
                })
                .user(customerMessage)
                .call()
                .content();
    }

}
