package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.CarParkInfo;

import java.util.List;

public interface CarParkInfoService {

    List<CarParkInfo> findAll();

    CarParkInfo findById(Long id);

//    CarParkInfo findByCarParkNumber(String carParkNumber);

    CarParkInfo save(CarParkInfo carParkInfo);

    void delete(CarParkInfo carParkInfo);
}
