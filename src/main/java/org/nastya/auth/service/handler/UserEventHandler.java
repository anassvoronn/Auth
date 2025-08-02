package org.nastya.auth.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nastya.auth.entity.User;
import org.nastya.auth.repository.UserRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final UserRepository userRepository;

    @HandleAfterCreate
    public void handleUserCreate(User user) {
        log.info("Handling user create event for user: {}", user.getUsername());
    }

    @HandleAfterSave
    public void handleUserSave(User user) {
        log.info("Handling user save event for user: {}", user.getUsername());
    }

    @HandleAfterDelete
    public void handleUserDelete(User user) {
        log.info("Handling user delete event for user: {}", user.getUsername());
    }
}
