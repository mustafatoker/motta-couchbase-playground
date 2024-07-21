package com.mustafatoker.motta.auth.filter;

import com.mustafatoker.motta.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        var hasBearerToken = Objects.nonNull(header) && header.startsWith("Bearer ");

        if (hasBearerToken) {
            var token = header.substring(7);
            var username = jwtUtil.extractUsername(token);
            var userId = jwtUtil.extractUserId(token);

            var isUsernamePresentAndNotAuthenticated = (Objects.nonNull(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null);

            if (isUsernamePresentAndNotAuthenticated) {
                var userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());

                    authenticationToken.setDetails(new CustomWebAuthenticationDetails(request, userId));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
