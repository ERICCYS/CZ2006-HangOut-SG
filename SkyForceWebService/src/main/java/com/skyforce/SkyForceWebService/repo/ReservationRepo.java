package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation,Integer>{
    // find a reservation by id
    Reservation findById(Long id);

    // insert one reservation
    Reservation save(Reservation reservation);

    // delete one reservation
    void delete(Reservation reservation);
}
