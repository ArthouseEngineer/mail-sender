package com.sbt.mailsender.api;

import com.sbt.mailsender.model.User;

import java.util.List;

public interface UserRepositoryService {
    List<User> getUserByBirthday(int month, int day);
    List<User> getAllUsers();
}
