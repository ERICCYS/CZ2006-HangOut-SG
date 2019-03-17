package com.skyforce.SkyForceWebService.service;


import com.skyforce.SkyForceWebService.model.Admin;
import com.skyforce.SkyForceWebService.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Override
    public List<Admin> findAll() {
        return adminRepo.findAll();
    }

    @Override
    public Admin findById(Long id) {
        return adminRepo.findById(id);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        adminRepo.delete(admin);
    }
}
