package com.mustafatoker.motta.auth.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@RequiredArgsConstructor
public class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {
    private final String userId;

    @Override
    public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context, userId);
    }
}