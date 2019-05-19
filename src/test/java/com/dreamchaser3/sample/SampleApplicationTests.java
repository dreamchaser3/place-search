package com.dreamchaser3.sample;

import com.dreamchaser3.sample.model.UserDTO;
import com.dreamchaser3.sample.repository.UserRepository;
import com.dreamchaser3.sample.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleApplicationTests {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        authenticationService.signUp(new UserDTO("admin", "admin"));
        authenticationService.signIn(new UserDTO("admin", "admin"));
    }

    @Test
    public void signUpTest() {
        String uid = "dreamchaser3";
        String password = "test";
        authenticationService.signUp(new UserDTO(uid, password));
        assertThat(passwordEncoder.matches(password,
                userRepository.findByUid(uid).getPassword()), is(true));
    }
}
