package com.dreamchaser3.sample.controller;

import com.dreamchaser3.sample.model.StringResult;
import com.dreamchaser3.sample.model.UserDTO;
import com.dreamchaser3.sample.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<StringResult> signIn(@RequestBody UserDTO userDTO) {
        try {
            authenticationService.signIn(userDTO);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new StringResult("Sign In Success"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new StringResult("Sign In Fail. cause: " + e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<StringResult> signUp(@RequestBody UserDTO userDTO) {
        try {
            authenticationService.signUp(userDTO);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new StringResult("Sign Up Success"));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new StringResult("Sign Up Fail. cause: " + e.getMessage()));
        }
    }
}
