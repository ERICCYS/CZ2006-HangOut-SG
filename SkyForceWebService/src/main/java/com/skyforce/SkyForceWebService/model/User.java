package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@MappedSuperclass
public class User {

    // TODO: Add user authentication

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

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public User() {

    }

    public User(Long id, String firstName, String lastName, GenderEnm gender, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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

    // email is not updatable

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String hashPassword (String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        BigInteger no = new BigInteger(1, hash);
        String hashedPassword = no.toString(16);
        while (hashedPassword.length() < 32) {
            hashedPassword = "0" + hashedPassword;
        }
        return hashedPassword;
    }
}
