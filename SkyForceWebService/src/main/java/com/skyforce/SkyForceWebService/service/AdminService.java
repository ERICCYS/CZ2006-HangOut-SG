package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> findAll();

    Admin findById(Long id);

    Admin findByEmail(String email);

    Admin save(Admin admin);

    void delete(Admin admin);
}
