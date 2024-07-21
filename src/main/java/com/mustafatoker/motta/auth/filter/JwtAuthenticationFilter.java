package com.mustafatoker.motta.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustafatoker.motta.auth.domain.User;
import com.mustafatoker.motta.auth.exception.AttemptAuthenticationHasBeenFailedException;
import com.mustafatoker.motta.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            var creds = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.get("username"), creds.get("password")));

        } catch (IOException e) {
            throw new AttemptAuthenticationHasBeenFailedException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        var user = ((User) authResult.getPrincipal());
        var token = jwtUtil.generateToken(user.getId(), authResult.getName());

        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(Map.of("token", token)));
    }
}
