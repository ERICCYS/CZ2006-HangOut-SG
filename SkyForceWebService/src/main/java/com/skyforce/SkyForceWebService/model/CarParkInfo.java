package com.skyforce.SkyForceWebService.model;

public class CarParkInfo {
    private Long lotsAvailable;

    private Long totalLots;

    private String lotType;

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
}
