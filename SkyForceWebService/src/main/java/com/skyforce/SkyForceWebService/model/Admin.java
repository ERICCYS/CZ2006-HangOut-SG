package com.skyforce.SkyForceWebService.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends User {

    public Admin() {
    }
}
