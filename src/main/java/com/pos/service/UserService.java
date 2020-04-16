package com.pos.service;

import com.pos.entity.User;

public interface UserService {
    public User findUserByEmail(String email);

    public void saveUser(User user);
}