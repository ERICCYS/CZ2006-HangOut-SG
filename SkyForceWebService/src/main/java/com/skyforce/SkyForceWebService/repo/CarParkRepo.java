package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.CarParkAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarParkRepo extends JpaRepository<CarParkAvailability, Integer> {

    CarParkAvailability findById(Long id);

    CarParkAvailability findByCarParkNumber(String carParkNumber);

    CarParkAvailability save(CarParkAvailability carParkAvailability);

    void delete(CarParkAvailability carParkAvailability);
}
