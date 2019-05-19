package com.dreamchaser3.sample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 로그성 테이블
 */

@Getter
@NoArgsConstructor
@Entity
public class UserSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String uid;
    @Column
    private String keyword;

    public UserSearchHistory(String uid, String keyword) {
        this.uid = uid;
        this.keyword = keyword;
    }
}
