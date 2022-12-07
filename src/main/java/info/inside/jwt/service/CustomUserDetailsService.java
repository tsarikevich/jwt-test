package info.inside.jwt.service;

import info.inside.jwt.entities.User;
import info.inside.jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userFromDB = userRepository.findByName(userName);
        if (userFromDB.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(userFromDB.get().getName())
                .password(userFromDB.get().getPassword())
                .roles(userFromDB.get().getRole())
                .build();
    }
}
