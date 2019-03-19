package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin findById(Long id);

    Admin findByEmail(String email);

    Admin save(Admin admin);

    void delete(Admin admin);
}
