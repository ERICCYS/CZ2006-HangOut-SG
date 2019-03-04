package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.model.Vendor;
import com.skyforce.SkyForceWebService.service.ShopService;
import com.skyforce.SkyForceWebService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VendorController {

    // TODO:  Change http status for different exceptions

    // TODO: add service for vendor to add shop

    @Autowired
    VendorService vendorService;

    @Autowired
    ShopService shopService;

    private AtomicLong nextId = new AtomicLong();

    // TODO: Need a machanism to remember the last assigned id
    private long getRecentId () {
        List<Vendor> vendors = vendorService.findAll();
        Long recentId = 0L;
        for (Vendor vendor : vendors) {
            if (vendor.getId() > recentId) {
                recentId = vendor.getId();
            }
        }
        return recentId;
    }

    @GetMapping("/vendor")
    public String getAllVendors() {
        List<Vendor> vendors = vendorService.findAll();
        return "there are a brunch of vendors " + vendors;
    }

    @GetMapping("/vendor/{id}")
    public String getVendorById(@PathVariable("id") Long id) {
        Vendor vendor = vendorService.findVendorById(id);
        return "find vendor by id: " + vendor;
    }

    @PostMapping("/vendor")
    @ResponseStatus(HttpStatus.CREATED)
    public String createVendor(@Valid @RequestBody Vendor vendor) {
        vendor.setId(nextId.incrementAndGet());
        return "post success" + vendorService.save(vendor);
    }

    @PutMapping("/vendor/{id}/addshop")
    public String addShop (@PathVariable("id") Long id, @Valid @RequestBody Shop shop) {
        Vendor oldVendor = vendorService.findVendorById(id);
        shop.setId(nextId.incrementAndGet());
        oldVendor.addShop(new Shop(shop.getId(), shop.getName(), shop.getContactNumber(), shop.getContactEmail()));
        return  "add shop success" + vendorService.save(oldVendor) ;
    }

    @PutMapping("/vendor/{vendorId}/delshop/{shopId}")
    public String deleteShop (@PathVariable("vendorId") Long vendorId, @PathVariable("shopId") Long shopId) {
        Vendor oldVendor = vendorService.findVendorById(vendorId);
        Shop shop = shopService.findShopById(shopId);
        System.out.println(shop);
        oldVendor.removeShop(shop);
        return "Vendor after delete the shop" + vendorService.save(oldVendor);

    }


    @PutMapping("/vendor/{id}")
    public String updateVendorById(@PathVariable("id") Long id, @Valid @RequestBody Vendor vendor) {
        Vendor oldVendor = vendorService.findVendorById(id);
        oldVendor.setFirstName(vendor.getFirstName());
        oldVendor.setLastName(vendor.getLastName());
        oldVendor.setGender(vendor.getGender());
        oldVendor.setShops(vendor.getShops());
        Vendor updatedVendor = vendorService.save(oldVendor);
        return "updated Vendor" + updatedVendor;
    }


    @DeleteMapping("/vendor/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable(value = "id") Long id) {
        Vendor vendor = vendorService.findVendorById(id);
        vendorService.delete(vendor);
        return ResponseEntity.ok().build();
    }
}
