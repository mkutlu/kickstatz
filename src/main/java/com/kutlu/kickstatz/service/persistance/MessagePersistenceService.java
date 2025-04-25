package com.kutlu.kickstatz.service.persistance;

import com.kutlu.kickstatz.model.ChatMessage;
import com.kutlu.kickstatz.model.ChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessagePersistenceService {

    private static final Logger logger = LoggerFactory.getLogger(MessagePersistenceService.class);

    /**
     * Saves the chat message to the PostgreSQL database.
     * Logs the saving process.
     *
     * @param message The chat message to be saved.
     */
    public void saveMessage(ChatMessage message) {
        // Log the message saving process
        logger.info("Saving message with ID: {}", message.getId());

        // Implement PostgreSQL message saving logic here (e.g., using JPA or JDBC)
        // Example: messageRepository.save(message);

        // Log successful message saving
        logger.info("Message with ID: {} saved successfully.", message.getId());
    }

    /**
     * Saves the chat user to the PostgreSQL database.
     * Logs the saving process.
     *
     * @param user The chat user to be saved.
     */
    public void saveUser(ChatUser user) {
        // Log the user saving process
        logger.info("Saving user with ID: {}", user.getId());

        // Implement PostgreSQL user saving logic here (e.g., using JPA or JDBC)
        // Example: userRepository.save(user);

        // Log successful user saving
        logger.info("User with ID: {} saved successfully.", user.getId());
    }
}