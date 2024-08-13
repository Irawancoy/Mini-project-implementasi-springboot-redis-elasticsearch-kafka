package com.example.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // Mendefinisikan bean untuk RedisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Mengatur connection factory, yang digunakan untuk membuat koneksi ke Redis
        template.setConnectionFactory(redisConnectionFactory);

        // Mengatur serializer untuk kunci (key)
        template.setKeySerializer(new StringRedisSerializer());
        // Mengatur serializer untuk nilai (value) agar diserialisasi ke/dari JSON
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}


