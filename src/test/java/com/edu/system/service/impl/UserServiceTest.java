package com.edu.system.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.system.repository.UserRepository;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testEncodeAndDecodeUser() throws ServiceException {
        User user = new User();
        user.setLogin("test");
        when(userRepository.findByLogin("test")).thenReturn(Optional.of(user));
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        String token = userService.createUserToken(user);
        User retrievedUser = userService.retrieveUserByToken(token);
        assertEquals(user, retrievedUser);

    }
}
