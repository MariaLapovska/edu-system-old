package com.edu.system.service;

import java.util.List;

import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;

public interface UserAttemptService {
    UserAttempt getUserAttempt(User user, Test test);
    List<UserAttempt> getUserAttempts(User user);
    UserAttempt update(UserAttempt userAttempt);
}
