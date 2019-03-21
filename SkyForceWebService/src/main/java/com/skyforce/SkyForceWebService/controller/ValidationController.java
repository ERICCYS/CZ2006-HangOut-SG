package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.model.Admin;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.User;
import com.skyforce.SkyForceWebService.model.Vendor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ValidationController {

    public static String UserSignIn (User user, String password) throws NoSuchAlgorithmException {

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
        try
        {
            String text = "" + userId + "|" + userType;
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            accessToken = encoder.encodeToString(encrypted);
            System.out.println("encrypted, user access token");
            System.out.println(accessToken);
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            String decrypted = new String(cipher.doFinal(encrypted), "UTF-8");
            System.out.println("decrypted, user access token");
            System.out.println(decrypted);
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
            String key = "Bar12345Bar12345"; // 128 bit key

            Cipher cipher = Cipher.getInstance("AES");
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            decrypted = new String(cipher.doFinal(cipherText), "UTF-8");
            System.out.println("decrypted, user access token");
            System.out.println(decrypted);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;

    }
}
