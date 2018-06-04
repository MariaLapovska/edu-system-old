package com.edu.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;

public interface UserAttemptRepository extends CrudRepository<UserAttempt, Long> {
    Optional<UserAttempt> findByUserAndTest(User user, Test test);
    List<UserAttempt> findByUser(User user);
}