package com.games.rps.service;

import com.games.rps.config.JwtTokenUtil;
import com.games.rps.dto.JwtTokenDTO;
import com.games.rps.dto.LoginUserDTO;
import com.games.rps.entity.User;
import com.games.rps.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthenticateApiService implements IAuthenticateApiService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final GamerUserDetailsService gamerUserDetailsService;

    @Autowired
    public AuthenticateApiService(@NonNull UserRepository userRepository, @NonNull PasswordEncoder passwordEncoder,
                                  @NonNull JwtTokenUtil jwtTokenUtil, @NonNull GamerUserDetailsService gamerUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.gamerUserDetailsService = gamerUserDetailsService;
    }

    @Override
    public JwtTokenDTO authenticate(LoginUserDTO loginUser) {
        Optional<User> optionalUser = userRepository.findByUsername(loginUser.getUsername());
        optionalUser.ifPresent(it -> validatePassword(it, loginUser));
        User user = optionalUser.orElseGet(()  -> save(loginUser));
        final String token = jwtTokenUtil.generateToken(gamerUserDetailsService.getUserDetails(user));
        return new JwtTokenDTO().token(token);
    }

    private void validatePassword(User user, LoginUserDTO loginUser) {
        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Login or password is not valid");
        }
    }

    private User save(LoginUserDTO loginUser) {
        User user = new User();
        user.setEnabled(true);
        user.setUsername(loginUser.getUsername());
        user.setPassword(passwordEncoder.encode(loginUser.getPassword()));
        return userRepository.save(user);
    }
}
