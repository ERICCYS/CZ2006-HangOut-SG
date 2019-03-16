package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Admin;
import com.skyforce.SkyForceWebService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/admin")
    public String getAllAdmins() {
        List<Admin> admins = adminService.findAll();
        return JSONConvert.JSONConverter(admins);
    }

    @GetMapping("/admin/{id}")
    public String getAdminById(@PathVariable("id") Long id) {
        Admin admin = adminService.findById(id);
        return JSONConvert.JSONConverter(admin);
    }

    @GetMapping("/admin/signin/{email}/{password}")
    public String signInAdmin (@PathVariable("email") String email, @PathVariable("password") String password) throws NoSuchAlgorithmException {
        Admin admin = adminService.findByEmail(email);
        return ValidationController.UserSignIn(admin, password);
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Email or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAdmin (@Valid @RequestBody Admin admin) throws NoSuchAlgorithmException {
        admin.setId(nextId.incrementAndGet());
        String hashedPassword = admin.hashPassword(admin.getPassword());
        admin.setPassword(hashedPassword);
        return JSONConvert.JSONConverter(adminService.save(admin));
    }

    // Delete admin account
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) {
        Admin admin = adminService.findById(id);
        adminService.delete(admin);
        return ResponseEntity.ok().build();
    }

}
