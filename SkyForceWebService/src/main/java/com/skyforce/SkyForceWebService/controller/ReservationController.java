package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.model.Reservation;
import com.skyforce.SkyForceWebService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/reservation")
    public String getAllReservations(){
        List<Reservation> reservations = reservationService.findAll();
        return "There are all reservations" + reservations;
    }

    @GetMapping("/reservation/{id}")
    public String getAlReservationsById(@PathVariable("id") Long id){
        Reservation reservation = reservationService.findReservationById(id);
        return "There are all reservations" + reservation;
    }

    // create reservation
    @GetMapping("/reservation/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createReservation(@Valid @RequestBody Reservation reservation){
//        reservation.setId(nextId.incrementAndGet());
        return "post success" + reservationService.save(reservation);
    }

    // delete reservation
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservsation(@PathVariable(value = "id") Long id){
        Reservation reservation = reservationService.findReservationById(id);
        reservationService.delete(reservation);
        return ResponseEntity.ok().build();
    }

    // confirm reservation


    // call shop ???

}
