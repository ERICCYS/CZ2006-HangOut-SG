package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.CarParkAvailability;

import java.util.List;
import java.util.Optional;

public interface CarParkService {

    List<CarParkAvailability> findAll();

    Optional<CarParkAvailability> findById(String id);

    CarParkAvailability save(CarParkAvailability carParkAvailability);

    void delete(CarParkAvailability carParkAvailability);
}
