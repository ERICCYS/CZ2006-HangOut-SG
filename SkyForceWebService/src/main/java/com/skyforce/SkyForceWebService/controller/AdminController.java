package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


}
