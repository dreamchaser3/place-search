package com.dreamchaser3.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String uid;
    private String password;

    public User toUserEntity() {
        return new User(uid, password);
    }
}
