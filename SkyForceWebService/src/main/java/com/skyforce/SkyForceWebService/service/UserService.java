package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(Long id);

    User save(User user);

    void delete(User user);
}
