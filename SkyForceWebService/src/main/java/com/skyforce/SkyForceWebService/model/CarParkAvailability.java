package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CarParkAvailability")
public class CarParkAvailability {

    @Id
    @Column(name="ID", unique = true, nullable = false)
    private String id;

    @ElementCollection
    private List<CarParkInfo> carParkInfos;

    @Column(name = "UPDATE_DATETIME", nullable = false)
    private String updateTime;

    public CarParkAvailability() {
    }

    public CarParkAvailability(String id, List<CarParkInfo> carParkInfos, String updateTime) {
        this.id = id;
        this.carParkInfos = carParkInfos;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
