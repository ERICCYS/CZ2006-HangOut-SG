package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    // find all the users

    // insert one user

    // find user by email
    User findById(Long id);

    User save(User user);

    void delete(User user);
}
