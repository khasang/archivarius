package io.khasang.archivarius.config;

import io.khasang.archivarius.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Message message(){
        return new Message("Welcome to archivarius app!");
    }
    
}
