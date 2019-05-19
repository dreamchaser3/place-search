package com.dreamchaser3.sample.service;

import com.dreamchaser3.sample.model.User;
import com.dreamchaser3.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUid(uid))
                .orElseThrow(() -> new UsernameNotFoundException("User not exist. uid: " + uid));
        Set<GrantedAuthority> grantedAuthorities = Set.of(new SimpleGrantedAuthority("member"));
        return new org.springframework.security.core.userdetails.User(user.getUid(), user.getPassword(), grantedAuthorities);
    }
}
