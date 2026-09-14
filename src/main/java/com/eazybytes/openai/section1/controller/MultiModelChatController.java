package com.eazybytes.openai.section1.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api")
public class MultiModelChatController {
    @Autowired
    private final ChatClient ollamaChatClient;
    @Autowired
    private final ChatClient bedrockChatClient;
    @Autowired
    private final ChatClient openAiChatClient;
    public MultiModelChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient,@Qualifier("bedrockChatClient") ChatClient bedrockChatClient,@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.ollamaChatClient = ollamaChatClient;
        this.bedrockChatClient = bedrockChatClient;
        this.openAiChatClient = openAiChatClient;
    }
//    @GetMapping("/openai/chat")
    public String openAiChat(@RequestParam("message") String message) {
        return openAiChatClient.prompt(message).call().content();
    }
//    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }
//    @GetMapping("/bedrock/chat")
    public String bedrockChat(@RequestParam("message") String message) {
        return bedrockChatClient.prompt(message).call().content();
    }
}
