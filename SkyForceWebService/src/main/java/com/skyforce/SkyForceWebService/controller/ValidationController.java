package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ValidationController {

    private static final String key = "Bar12345Bar12345";

    public static String UserSignIn (User user, String password) throws NoSuchAlgorithmException {

        String hashedPassword = user.hashPassword(password);

        if (hashedPassword.equals(user.getPassword())) {
            return JSONConvert.JSONConverter(user);
        } else {
            throw new IllegalArgumentException();
        }

    }


    public static String getAccessToken(Long userId, String userType) {

        String accessToken = "";
        try
        {
            String text = "" + userId + "|" + userType;
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            accessToken = encoder.encodeToString(encrypted);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            String decrypted = new String(cipher.doFinal(encrypted), "UTF-8");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return accessToken;
    }

    public static String decryptAccessToken(String accessToken) {

        String decrypted = "";
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            decrypted = new String(cipher.doFinal(cipherText), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }
}
