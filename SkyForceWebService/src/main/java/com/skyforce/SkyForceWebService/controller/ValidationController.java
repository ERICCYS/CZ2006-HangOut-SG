package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.User;

import java.security.NoSuchAlgorithmException;

public class ValidationController {

    public static String UserSignIn (User user, String password) throws NoSuchAlgorithmException {

        String hashedPassword = user.hashPassword(password);

        if (hashedPassword.equals(user.getPassword())) {
            return JSONConvert.JSONConverter(user);
        } else {
            throw new IllegalArgumentException();
        }

    }
}
