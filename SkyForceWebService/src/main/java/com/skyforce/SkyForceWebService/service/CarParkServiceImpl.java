package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.CarParkAvailability;
import com.skyforce.SkyForceWebService.repo.CarParkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarParkServiceImpl implements CarParkService {

    @Autowired
    CarParkRepo carParkRepo;


    @Override
    public List<CarParkAvailability> findAll() {
        return carParkRepo.findAll();
    }

    @Override
    public CarParkAvailability findById(Long id) {
        return carParkRepo.findById(id);
    }

    @Override
    public CarParkAvailability findByCarParkNumber(String carParkNumber) {
        return carParkRepo.findByCarParkNumber(carParkNumber);
    }

    @Override
    public CarParkAvailability save(CarParkAvailability carParkAvailability) {
        return carParkRepo.save(carParkAvailability);
    }

    @Override
    public void delete(CarParkAvailability carParkAvailability) {
        carParkRepo.delete(carParkAvailability);
    }
}
