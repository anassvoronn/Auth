package org.nastya.auth.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nastya.auth.entity.User;
import org.nastya.auth.service.AuthService;
import org.nastya.auth.service.CustomUserDetailsService;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryEventHandler(User.class)
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final AuthService authService;
    private final CustomUserDetailsService customUserDetailsService;

    @HandleAfterCreate
    public void handleAfterUserCreate(User user) {
        log.info("Handling user create event for user: {}", user.getUsername());
        log.info("user created: {}", user.getUsername());
        log.info("a user with a username {}, password {} and email {} has been created", user.getUsername(), user.getPassword(), user.getEmail());
    }

    @HandleAfterSave
    public void handleAfterUserSave(User user) {
        log.info("Handling user save event for user: {}", user.getUsername());
        log.info("user changed: {}", user.getUsername());
        log.info("a user with a username {}, password {} and email {} has been changed", user.getUsername(), user.getPassword(), user.getEmail());
    }

    @HandleAfterDelete
    public void handleAfterUserDelete(User user) {
        log.info("Handling user delete event for user: {}", user.getUsername());
        log.info("user deleted: {}", user.getUsername());
        log.info("a user with a username {}, password {} and email {} has been deleted", user.getUsername(), user.getPassword(), user.getEmail());
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
}
