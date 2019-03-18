package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "ShopVerification")
public class ShopVerification {

    @Id
    @Column(name="ID", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SHOP_ID")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="VENDOR_ID")
    private Vendor vendor;

    @Column(name="CERTIFICATE", nullable = false)
    private URL certificate;

    @Column(name="PROCESSED")
    private boolean processed;

    @Column(name = "APPROVED")
    private boolean approved;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ADMIN")
    private Admin admin;

    @Column(name = "details")
    private String details;

    public ShopVerification() {
    }

    public ShopVerification(Long id, Shop shop, boolean processed, boolean approved, Admin admin, String details) {
        this.id = id;
        this.processed = processed;
        this.approved = approved;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ShopVerification{" +
                "id=" + id +
                ", shop=" + shop +
                ", certificate=" + certificate +
                ", processed=" + processed +
                ", approved=" + approved +
                ", admin=" + admin +
                ", details='" + details + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public URL getCertificate() {
        return certificate;
    }

    public void setCertificate(URL certificate) {
        this.certificate = certificate;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
