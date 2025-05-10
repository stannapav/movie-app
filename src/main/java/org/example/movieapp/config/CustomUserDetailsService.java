package org.example.movieapp.config;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.entities.UserModel;
import org.example.movieapp.db.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("user")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
