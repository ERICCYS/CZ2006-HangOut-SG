package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Vendor;
import com.skyforce.SkyForceWebService.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepo vendorRepo;

    @Override
    public List<Vendor> findAll() {
        return vendorRepo.findAll();
    }

    @Override
    public Vendor findVendorById(Long id) {
        return vendorRepo.findById(id);
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepo.save(vendor);
    }

    @Override
    public void delete(Vendor vendor) {
        vendorRepo.delete(vendor);
    }
}
