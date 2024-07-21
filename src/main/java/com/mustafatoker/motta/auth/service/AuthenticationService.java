package com.mustafatoker.motta.auth.service;

import com.mustafatoker.motta.auth.domain.Password;
import com.mustafatoker.motta.auth.domain.User;
import com.mustafatoker.motta.auth.domain.Username;
import com.mustafatoker.motta.auth.exception.UserNotFoundException;
import com.mustafatoker.motta.auth.exception.UsernameAlreadyExistsException;
import com.mustafatoker.motta.auth.repository.UserRepository;
import com.mustafatoker.motta.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void registerUser(Username username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + username.getValue() + " already exists");
        }

        var user = createUser(username, rawPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userUsername = Username.of(username);
        var user = userRepository.findByUsername(userUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    public String authenticate(Username username, String password) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Invalid username or password"));

        if (!user.checkPassword(password, passwordEncoder)) {
            throw new UserNotFoundException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getId(), user.getUsername());
    }

    private User createUser(Username username, String rawPassword) {
        var password = Password.ofRaw(rawPassword, passwordEncoder);

        return User.of(username, password);
    }
}