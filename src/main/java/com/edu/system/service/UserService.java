package com.edu.system.service;

import com.edu.system.vo.User;

public interface UserService {
    String authenticate(String login, String password) throws ServiceException;
    User register(User user) throws ServiceException;
}
