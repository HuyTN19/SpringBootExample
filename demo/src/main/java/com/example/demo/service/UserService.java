package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;

public interface UserService {
    public User createUser(UserModel userModel);

    public User read(Long id);

    User update(User user, Long id);

    void delete(Long id);

}
