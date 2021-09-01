package com.kodilla.ebooklibrary.mapper;

import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    @Autowired
    private UserService userService;

    public User mapToUserNullable(long userId) {
        return userService.getUserById(userId).orElse(null);
    }
}