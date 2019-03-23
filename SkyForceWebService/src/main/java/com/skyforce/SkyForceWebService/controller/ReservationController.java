package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.model.Reservation;
import com.skyforce.SkyForceWebService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return "There are all reservations" + reservations;
    }

    @GetMapping("/reservation/{id}")
    public String getAlReservationsById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.findReservationById(id);
        return "There are all reservations" + reservation;
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.CREATED)
    public String createReservation(
            @RequestParam Long customerId,
            @Valid @RequestBody Reservation reservation,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        return "post success" + reservationService.save(reservation);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservsation(
            @PathVariable(value = "id") Long id,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        Reservation reservation = reservationService.findReservationById(id);
        reservationService.delete(reservation);
        return ResponseEntity.ok().build();
    }

    // TODO: ADD RESERVATION APPROVAL OR REJECTION

}
