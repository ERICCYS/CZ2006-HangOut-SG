package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Vendor;

import java.util.List;

public interface VendorService {

    List<Vendor> findAll();

    Vendor findVendorById(Long id);

    Vendor findVendorByEmail(String email);

    Vendor save(Vendor vendor);

    void delete(Vendor vendor);
}
