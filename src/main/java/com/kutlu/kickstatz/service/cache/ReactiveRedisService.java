package com.kutlu.kickstatz.service.cache;

import com.kutlu.kickstatz.model.ChatMessage;
import com.kutlu.kickstatz.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveRedisService {

    @Autowired
    private ReactiveRedisTemplate<String, ChatMessage> messageRedisTemplate;

    @Autowired
    private ReactiveRedisTemplate<String, ChatUser> userRedisTemplate;

    public Mono<Boolean> saveLastMessage(ChatMessage message) {
        ReactiveValueOperations<String, ChatMessage> ops = messageRedisTemplate.opsForValue();
        String key = "last_message:channel:" + message.getChannelId();
        return ops.set(key, message);
    }

    public Mono<Boolean> saveUser(ChatUser user) {
        ReactiveValueOperations<String, ChatUser> ops = userRedisTemplate.opsForValue();
        String key = "user:channel:" + user.getChannelId() + ":user:" + user.getId();
        return ops.set(key, user);
    }
}