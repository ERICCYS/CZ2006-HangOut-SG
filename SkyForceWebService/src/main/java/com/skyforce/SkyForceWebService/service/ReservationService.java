package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findReservationById(Long id);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);
}
