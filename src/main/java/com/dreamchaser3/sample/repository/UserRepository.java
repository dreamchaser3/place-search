package com.dreamchaser3.sample.repository;

import com.dreamchaser3.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
}
