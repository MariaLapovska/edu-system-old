package com.edu.system.service;

import java.util.Optional;

import com.edu.system.vo.User;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;
    User register(User user) throws ServiceException;
}
