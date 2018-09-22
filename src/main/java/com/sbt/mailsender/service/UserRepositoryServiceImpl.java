package com.sbt.mailsender.service;

import com.sbt.mailsender.api.*;
import com.sbt.mailsender.model.User;
import com.sbt.mailsender.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {


    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUserByBirthday(int month, int day) {
        return userRepository.findByMatchMonthAndMatchDay(month, day);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
