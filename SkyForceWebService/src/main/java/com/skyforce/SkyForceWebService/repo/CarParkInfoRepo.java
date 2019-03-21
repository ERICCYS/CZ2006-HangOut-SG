package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.CarParkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarParkInfoRepo extends JpaRepository<CarParkInfo, Integer> {

    CarParkInfo findById(Long id);

//    CarParkInfo findByCarParkNumber(String carParkNumber);

    CarParkInfo save(CarParkInfo carParkInfo);

    void delete(CarParkInfo carParkInfo);

}
