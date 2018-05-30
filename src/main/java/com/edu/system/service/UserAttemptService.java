package com.edu.system.service;

import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;

public interface UserAttemptService {
    UserAttempt getUserAttempt(User user, Test test);
    UserAttempt update(UserAttempt userAttempt);
}
