package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepo extends JpaRepository<Admin, Integer>{

    // find a admin by id
    Admin findById(Long id);

    // find a admin by email
    Admin findByEmail(String email);

    // insert one admin
    Admin save(Admin admin);

    // delete one admin
    void delete(Admin admin);
}
