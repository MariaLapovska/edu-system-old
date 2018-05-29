package com.edu.system.service.impl;


import java.util.Base64;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.UserRepository;
import com.edu.system.service.ServiceException;
import com.edu.system.service.UserService;
import com.edu.system.vo.User;

@Service
public class UserServiceImpl implements UserService {

    private final static String SECRET_KEY = "Qfsx3h3VjMUWlZal";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        return userRepository.findByLoginAndPass(login, password);
    }

    @Override
    public User register(User user) throws ServiceException {
        if(userRepository.findByLogin(user.getLogin()).isPresent()){
            throw new ServiceException("User already exist. Login: " + user.getLogin());
        }
        return userRepository.save(user);
    }

    String createUserToken(User user) {
        String login = user.getLogin();
        String token = login + ":" + DigestUtils.md5Hex(login + SECRET_KEY);
        return new String(Base64.getEncoder().encode(token.getBytes()));
    }

    User retrieveUserByToken(String encodedToken) throws ServiceException {
        String token = new String(Base64.getDecoder().decode(encodedToken.getBytes()));
        String[] userInfo = token.split(":");
        if (userInfo.length == 2 && DigestUtils.md5Hex(userInfo[0] + SECRET_KEY).equals(userInfo[1])) {
            return userRepository.findByLogin(userInfo[0]).orElseThrow(() -> new ServiceException("Failed to parse user token: " + token));
        }
        throw new ServiceException("Failed to parse user token: " + token);
    }
}
