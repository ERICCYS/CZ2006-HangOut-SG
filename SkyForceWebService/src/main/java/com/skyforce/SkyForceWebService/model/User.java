package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class User {

    @Id
    @Column(name="ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 15)
    private String lastName;


    @Column(name = "GENDER", nullable = false, length = 15)
    private GenderEnm gender;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;
//
//    @Column(name = "PHONE", nullable = false, length = 30)
//    private String phone;

    public User() {

    }

    public User(Long id, String firstName, String lastName, GenderEnm gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderEnm getGender() {
        return gender;
    }

    public void setGender(GenderEnm gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
