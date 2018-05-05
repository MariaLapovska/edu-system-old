package com.edu.system.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login, String password);
    Optional<User> findByLogin(String login);
}