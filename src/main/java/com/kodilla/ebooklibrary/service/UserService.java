package com.kodilla.ebooklibrary.service;

import com.kodilla.ebooklibrary.domain.dto.LoginDto;
import com.kodilla.ebooklibrary.domain.dto.RegisteredDto;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    public static final int NOT_LOGGED_IN = -1;
    @Autowired
    private UserRepository userRepository;

    public RegisteredDto register(LoginDto loginDto) {
        Optional<User> user = userRepository.findByLogin(loginDto.getLogin());
        boolean isNew = !user.isPresent();
        User registeredUser = user.orElse(new User(loginDto.getLogin(), loginDto.getPassword()));
        registeredUser.setPassword(loginDto.getPassword());
        userRepository.save(registeredUser);
        return new RegisteredDto(registeredUser.getId(), isNew);
    }

    public long login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByLogin(loginDto.getLogin());
        if (user.isPresent()) {
            User retrievedUser = user.get();
            if (retrievedUser.getPassword().equals(loginDto.getPassword()))
                return retrievedUser.getId();
            else
                return NOT_LOGGED_IN;
        } else {
            return NOT_LOGGED_IN;
        }
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }
}
