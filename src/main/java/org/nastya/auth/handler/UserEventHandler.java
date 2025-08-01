package org.nastya.auth.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nastya.auth.entity.User;
import org.nastya.auth.repository.UserRepository;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryEventHandler(User.class)
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final UserRepository userRepository;

    @HandleAfterCreate
    public void handleUserCreate(User user) {
        log.info("Handling user create event for user: {}", user.getUsername());
        List<User> admins = userRepository.findByRole(user.getRole());
        log.info("Found {} users with role {}", admins.size(), user.getRole());
    }

    @HandleAfterSave
    public void handleUserUpdate(User user) {
        log.info("Handling user update event for user: {}", user.getUsername());
    }

    @HandleAfterDelete
    public void handleUserDelete(User user) {
        log.info("Handling user delete event for user: {}", user.getUsername());
    }
}
