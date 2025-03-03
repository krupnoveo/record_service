package ru.krupnoveo.edu.record_service.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {


    @Bean
    @Scope("prototype")
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
