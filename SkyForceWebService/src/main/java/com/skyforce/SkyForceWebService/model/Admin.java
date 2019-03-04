package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin extends User {

    // Admin need to be added manually with database query, not through this system

    public Admin() {
    }
}
