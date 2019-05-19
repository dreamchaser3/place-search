package com.dreamchaser3.sample.service;

import com.dreamchaser3.sample.model.UserDTO;
import com.dreamchaser3.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void signIn(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void signUp(UserDTO userDTO) {
        String rawPassword = userDTO.getPassword();
        if (userRepository.findByUid(userDTO.getUid()) != null) {
            throw new IllegalArgumentException("The ID already exists. uid: " + userDTO.getUid());
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userRepository.save(userDTO.toUserEntity());
        // auto sign-in
        signIn(new UserDTO(userDTO.getUid(), rawPassword));
    }
}
