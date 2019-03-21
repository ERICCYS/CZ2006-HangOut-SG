package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Vendor;
import com.skyforce.SkyForceWebService.service.ShopService;
import com.skyforce.SkyForceWebService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class VendorController {

    // TODO:  Change http status for different exceptions

    @Autowired
    VendorService vendorService;

    @Autowired
    ShopService shopService;

    @GetMapping("/vendors")
    public String getAllVendors() {
        List<Vendor> vendors = vendorService.findAll();
        return JSONConvert.JSONConverter(vendors);
    }
    // pass



    @GetMapping("/vendor")
    public String getVendorById(
            @RequestParam Long vendorId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == vendorId && info[1].equals("VENDOR")) {
            Vendor vendor = vendorService.findVendorById(vendorId);
            return JSONConvert.JSONConverter(vendor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/vendor/signin")
    public String signInVendor(
            @RequestParam String email,
            @RequestParam String password
    ) throws NoSuchAlgorithmException {
        Vendor vendor = vendorService.findVendorByEmail(email);
        return ValidationController.UserSignIn(vendor, password);
    }
    // pass

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Email or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @PostMapping("/vendor")
    @ResponseStatus(HttpStatus.CREATED)
    public String createVendor(
            @Valid @RequestBody Vendor vendor
    ) throws NoSuchAlgorithmException {
        String hashedPassword = vendor.hashPassword(vendor.getPassword());
        vendor.setPassword(hashedPassword);
        JSONConvert.JSONConverter(vendorService.save(vendor));
        return ValidationController.getAccessToken(vendor.getId(), "VENDOR");
    }


    @PutMapping("/vendor")
    public String updateVendorById(
            @RequestParam Long vendorId,
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody Vendor vendor
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == vendorId && info[1].equals("VENDOR")) {
            Vendor oldVendor = vendorService.findVendorById(vendorId);
            oldVendor.setFirstName(vendor.getFirstName());
            oldVendor.setLastName(vendor.getLastName());
            oldVendor.setGender(vendor.getGender());
            Vendor updatedVendor = vendorService.save(oldVendor);
            return JSONConvert.JSONConverter(updatedVendor);
        } else {
            throw new IllegalArgumentException();
        }
    }
    // pass

    @DeleteMapping("/vendor")
    public ResponseEntity<?> deleteVendor(
            @RequestParam Long vendorId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == vendorId && info[1].equals("VENDOR")) {
            Vendor vendor = vendorService.findVendorById(vendorId);
            vendorService.delete(vendor);
            return ResponseEntity.ok().build();
        } else {
            throw new IllegalArgumentException();
        }
    }
    // one null pointer exp when wrong access token was given
    // partially passed
}
