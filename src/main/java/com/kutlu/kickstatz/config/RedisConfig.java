package com.kutlu.kickstatz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.kutlu.kickstatz.model.ChatMessage;
import com.kutlu.kickstatz.model.ChatUser;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, ChatMessage> messageRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializer<ChatMessage> chatMessageSerializer = createSerializer(new GenericJackson2JsonRedisSerializer());
        RedisSerializationContext<String, ChatMessage> serializationContext = createSerializationContext(chatMessageSerializer);
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }



    @Bean
    public ReactiveRedisTemplate<String, ChatUser> userRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializer<ChatUser> chatUserSerializer = createSerializer(new GenericJackson2JsonRedisSerializer());
        RedisSerializationContext<String, ChatUser> serializationContext = createSerializationContext(chatUserSerializer);
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }


    private <T> RedisSerializationContext<String, T> createSerializationContext(RedisSerializer<T> serializer) {
        return RedisSerializationContext.<String, T>newSerializationContext(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(serializer)
                .value(serializer)
                .build();
    }

    private <T> RedisSerializer<T> createSerializer(RedisSerializer<?> serializer) {
        @SuppressWarnings("unchecked")
        RedisSerializer<T> casted = (RedisSerializer<T>) serializer;
        return casted;
    }


}