package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Reservation;
import com.skyforce.SkyForceWebService.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationRepo reservationRepo;

    public List<Reservation> findAll(){
        return reservationRepo.findAll();
    }

    public Reservation findReservationById(Long id){
        return reservationRepo.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationRepo.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationRepo.delete(reservation);
    }
}
