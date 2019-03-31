package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.model.Admin;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.User;
import com.skyforce.SkyForceWebService.model.Vendor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
public class ValidationController {

    private static final String key = "Bar12345Bar12345";

    public static String UserSignIn(User user, String password) throws NoSuchAlgorithmException {

        String hashedPassword = user.hashPassword(password);

        if (hashedPassword.equals(user.getPassword())) {
            if (user instanceof Customer)
                return getAccessToken(user.getId(), "CUSTOMER");
            if (user instanceof Vendor)
                return getAccessToken(user.getId(), "VENDOR");
            if (user instanceof Admin)
                return getAccessToken(user.getId(), "ADMIN");
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }


    public static String getAccessToken(Long userId, String userType) {

        String accessToken = "";
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public static String[] decryptAccessToken(String accessToken) {

        String decrypted = "";
        String[] info = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            decrypted = new String(cipher.doFinal(cipherText), "UTF-8");
            info = decrypted.split("\\|");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @GetMapping("/accessToken")
    public static String getUserId(
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        System.out.println(accessToken);
        String[] result = decryptAccessToken(accessToken);
        System.out.println(result);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        return result[0];
    }
}
