package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> findAll();

    // find a admin by id
    Admin findById(Long id);

    // find a admin by email
    Admin findByEmail(String email);

    // insert one admin
    Admin save(Admin admin);

    // delete one admin
    void delete(Admin admin);
}
