package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CarParkAvailability")
public class CarParkAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="CAR_PARK_NUMBER", nullable = false)
    private String carParkNumber;

    @OneToMany(mappedBy = "carParkAvailability", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CarParkInfo> carParkInfos;

    @Column(name = "UPDATE_DATETIME", nullable = false)
    private String updateTime;

    public CarParkAvailability() {
    }

    public CarParkAvailability(Long id, List<CarParkInfo> carParkInfos, String updateTime) {
        this.id = id;
        this.carParkInfos = carParkInfos;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarParkNumber() {
        return carParkNumber;
    }

    public void setCarParkNumber(String carParkNumber) {
        this.carParkNumber = carParkNumber;
    }

    public List<CarParkInfo> getCarParkInfos() {
        return carParkInfos;
    }

    public void setCarParkInfos(List<CarParkInfo> carParkInfos) {
        this.carParkInfos = carParkInfos;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void addCarParkInfo(CarParkInfo carParkInfo) {
        carParkInfos.add(carParkInfo);
        carParkInfo.setCarParkAvailability(this);
    }

    public void deleteCarParkInfo(CarParkInfo carParkInfo) {
        carParkInfos.remove(carParkInfo);
        carParkInfo.setCarParkAvailability(null);
    }
}
