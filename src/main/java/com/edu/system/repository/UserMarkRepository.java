package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.User;
import com.edu.system.vo.UserMark;

public interface UserMarkRepository extends CrudRepository<UserMark, Long> {
    List<UserMark> findByUser(User user);
}