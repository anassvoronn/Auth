package org.nastya.auth.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nastya.auth.entity.User;
import org.nastya.auth.service.AuthService;
import org.nastya.auth.service.CustomUserDetailsService;
import org.nastya.auth.service.KafkaProducerService;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RepositoryEventHandler(User.class)
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final AuthService authService;
    private final CustomUserDetailsService customUserDetailsService;
    private final KafkaProducerService kafkaProducerService;
    public static final String TOPIC_NAME = "test-topic";

    @HandleAfterCreate
    public void handleAfterUserCreate(User user) {
        HashMap<String, String> message = new HashMap<>();
        message.put("action", "created");
        message.put("username", user.getUsername());
        message.put("email", user.getEmail());
        message.put("adminEmails", String.join(", ", getAdminEmails()));
        kafkaProducerService.sendMessage(TOPIC_NAME, message);
    }

    @HandleAfterSave
    public void handleAfterUserSave(User user) {
        HashMap<String, String> message = new HashMap<>();
        message.put("action", "updated");
        message.put("username", user.getUsername());
        message.put("email", user.getEmail());
        message.put("adminEmails", String.join(", ", getAdminEmails()));
        kafkaProducerService.sendMessage(TOPIC_NAME, message);
    }

    @HandleAfterDelete
    public void handleAfterUserDelete(User user) {
        HashMap<String, String> message = new HashMap<>();
        message.put("action", "deleted");
        message.put("username", user.getUsername());
        message.put("email", user.getEmail());
        message.put("adminEmails", String.join(", ", getAdminEmails()));
        kafkaProducerService.sendMessage(TOPIC_NAME, message);
    }

    @HandleBeforeSave
    public void handleBeforeUserSave(User user) throws IllegalAccessException {
        checkCurrentUserAccess(user);
    }

    @HandleBeforeDelete
    public void handleBeforeUserDelete(User user) throws IllegalAccessException {
        checkCurrentUserAccess(user);
    }

    private void checkCurrentUserAccess(User user) throws IllegalAccessException {
        String currentUsername = authService.getCurrentUsername();
        User currentUser = customUserDetailsService.getUserByUsername(currentUsername);

        if (currentUser.equals(user)) {
            return;
        }
        List<String> roles = customUserDetailsService.getAdminRoles(currentUsername);
        if (roles.isEmpty()) {
            throw new IllegalAccessException("Only admin can do this action");
        }
    }

    private Set<String> getAdminEmails() {
        return customUserDetailsService.findAllAdmins()
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());
    }

}