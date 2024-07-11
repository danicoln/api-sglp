package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import com.sglp.sglp_api.api.dto.model.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/ia")
public class GPTResource {

    @Value("${spring.ai.openai.model}")
    private String model;

    @Value("${spring.ai.openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGPTRequest request = new ChatGPTRequest("gpt-3.5-turbo", prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiUrl, request, ChatGPTResponse.class);
        assert chatGPTResponse != null;
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

}
