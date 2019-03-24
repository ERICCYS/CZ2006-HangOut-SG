package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.CarParkInfo;
import com.skyforce.SkyForceWebService.repo.CarParkInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkInfoServiceImpl implements CarParkInfoService {


    @Autowired
    CarParkInfoRepo carParkInfoRepo;

    @Override
    public List<CarParkInfo> findAll() {
        return carParkInfoRepo.findAll();
    }

    @Override
    public CarParkInfo findById(Long id) {
        return carParkInfoRepo.findById(id);
    }
//
//    @Override
//    public CarParkInfo findByCarParkNumber(String carParkNumber) {
//        return carParkInfoRepo.findByCarParkNumber(carParkNumber);
//    }

    @Override
    public CarParkInfo save(CarParkInfo carParkInfo) {
        return carParkInfoRepo.save(carParkInfo);
    }

    @Override
    public void delete(CarParkInfo carParkInfo) {
        carParkInfoRepo.delete(carParkInfo);
    }
}
