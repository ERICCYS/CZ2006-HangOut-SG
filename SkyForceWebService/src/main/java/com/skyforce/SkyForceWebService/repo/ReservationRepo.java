package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    Reservation findById(Long id);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);
}
