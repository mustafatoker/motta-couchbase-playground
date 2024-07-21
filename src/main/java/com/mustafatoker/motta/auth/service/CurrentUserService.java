package com.mustafatoker.motta.auth.service;

import com.mustafatoker.motta.auth.exception.AuthenticationRequiredException;
import com.mustafatoker.motta.auth.filter.CustomWebAuthenticationDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentUserService {

    public String getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationRequiredException("Authentication required");
        }

        var authDetails = (CustomWebAuthenticationDetails) authentication.getDetails();

        log.info("Current user id: {}", authDetails.getUserId());

        return authDetails.getUserId();
    }
}
