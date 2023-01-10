package com.example.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorUtil {

    public String generateId(int len){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        String alphaNum = alphabet + number;
        StringBuilder id = new StringBuilder(23);
        for(int i = 0; i < len ; i++){
            int randIndex = (int) (alphaNum.length() * Math.random());
            id.append(alphaNum.charAt(randIndex));
        }
        return id.toString();
    }

    @Bean
    public String generateArtistId(){
        return "ART" + generateId(23);
    }

    @Bean
    public String generateSongId(){
        return "SNG" + generateId(23);
    }
}
