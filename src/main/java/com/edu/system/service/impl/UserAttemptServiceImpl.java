package com.edu.system.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.UserAttemptRepository;
import com.edu.system.service.UserAttemptService;
import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;

@Service
public class UserAttemptServiceImpl implements UserAttemptService {

    private final UserAttemptRepository userAttemptRepository;

    @Autowired
    public UserAttemptServiceImpl(UserAttemptRepository userAttemptRepository) {
        this.userAttemptRepository = userAttemptRepository;
    }

    @Override
    public UserAttempt getUserAttempt(User user, Test test) {
        Optional<UserAttempt> userAttemptOptional = userAttemptRepository.findByUserAndTest(user, test);
        if (userAttemptOptional.isPresent()) {
            return userAttemptOptional.get();
        } else {
            UserAttempt userAttempt = new UserAttempt();
            userAttempt.setTest(test);
            userAttempt.setUser(user);
            userAttempt.setCount(0);
            userAttempt.setArticle(test.getArticle());
            return userAttempt;
        }
    }

    @Override
    public List<UserAttempt> getUserAttempts(User user) {
        return userAttemptRepository.findByUser(user);
    }

    @Override
    public UserAttempt update(UserAttempt userAttempt) {
        return userAttemptRepository.save(userAttempt);
    }
}
