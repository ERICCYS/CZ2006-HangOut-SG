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

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admins")
    public String getAllAdmins() {
        List<Admin> admins = adminService.findAll();
        return JSONConvert.JSONConverter(admins);
    }

    @GetMapping("/admin")
    public String getAdminById(
            @RequestParam Long adminId) {
        Admin admin = adminService.findById(adminId);
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
    public String createAdmin (
            @Valid @RequestBody Admin admin
    ) throws NoSuchAlgorithmException {
        String hashedPassword = admin.hashPassword(admin.getPassword());
        admin.setPassword(hashedPassword);
        JSONConvert.JSONConverter(adminService.save(admin));
        return ValidationController.getAccessToken(admin.getId(), "ADMIN");
    }

    // Delete admin account
    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteAdmin(
            @RequestParam Long adminId) {
        Admin admin = adminService.findById(adminId);
        adminService.delete(admin);
        return ResponseEntity.ok().build();
    }

}
