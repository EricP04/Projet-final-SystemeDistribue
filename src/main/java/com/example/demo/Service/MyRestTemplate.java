package com.example.demo.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyRestTemplate {

    @Bean
    public org.springframework.web.client.RestTemplate restTemplate()
    {
        return new org.springframework.web.client.RestTemplate();
    }

}
