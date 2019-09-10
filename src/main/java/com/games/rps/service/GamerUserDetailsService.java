package com.games.rps.service;

import com.games.rps.entity.User;
import com.games.rps.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class GamerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public GamerUserDetailsService(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
        return getUserDetails(user);
    }

    public UserDetails getUserDetails(User user) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().toLowerCase(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, new ArrayList<>());
    }
}
