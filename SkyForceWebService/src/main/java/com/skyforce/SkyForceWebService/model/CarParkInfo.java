package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="CarParkInfo")
public class CarParkInfo {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="LOTS_AVAILABLE")
    private Long lotsAvailable;

    @Column(name="TOTAL_LOTS")
    private Long totalLots;

    @Column(name="LOT_TYPE")
    private String lotType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_PARK_ID")
    @JsonBackReference
    private CarParkAvailability carParkAvailability;

    public CarParkInfo() {
    }

    public CarParkInfo(Long lotsAvailable, Long totalLots, String lotType) {
        this.lotsAvailable = lotsAvailable;
        this.totalLots = totalLots;
        this.lotType = lotType;
    }

    public Long getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(Long lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    public Long getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(Long totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public CarParkAvailability getCarParkAvailability() {
        return carParkAvailability;
    }

    public void setCarParkAvailability(CarParkAvailability carParkAvailability) {
        this.carParkAvailability = carParkAvailability;
    }
}
