package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.Reservation;
import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.service.CustomerService;
import com.skyforce.SkyForceWebService.service.ReservationService;
import com.skyforce.SkyForceWebService.service.ShopService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShopService shopService;

    @GetMapping("/reservations")
    public String getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return JSONConvert.JSONConverter(reservations);
    }

    @GetMapping("reservation-customer-formatted")
    public String getReservationsByCustomerIdFormatted(
            @RequestParam Long customerId
    ) {
        List<Reservation> reservations = reservationService.findAll();
        List<Reservation> interested = new ArrayList<>();
        JSONArray relatedReservations = new JSONArray();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("SGT"));
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerId().equals(customerId)) {
                interested.add(reservation);
                Shop shop = shopService.findShopById(reservation.getShopId());
                JSONObject newReservation = new JSONObject();
                newReservation.put("shopName", shop.getName());
                newReservation.put("shopAddress", shop.getLocation());
                newReservation.put("arrivalTime", formatter.format(reservation.getArrivalTime()));
                relatedReservations.add(newReservation);
            }
        }

        return JSONConvert.JSONConverter(relatedReservations);
    }

    @GetMapping("/reservation")
    public String getReservationsById(
            @RequestParam Long reservationId
    ) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        return JSONConvert.JSONConverter(reservation);
    }

    @GetMapping("/reservation-customer")
    public String getReservationsByCustomerId(
            @RequestParam Long customerId
    ) {
        List<Reservation> reservations = reservationService.findAll();
        List<Reservation> interested = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerId().equals(customerId)) {
                interested.add(reservation);
            }
        }
        return JSONConvert.JSONConverter(interested);
    }

    @GetMapping("/reservation-shop")
    public String getReservationsByShopId(
            @RequestParam Long shopId
    ) {
        List<Reservation> reservations = reservationService.findAll();
        List<Reservation> interested = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getShopId().equals(shopId)) {
                interested.add(reservation);
            }
        }
        return JSONConvert.JSONConverter(interested);
    }

    @GetMapping("/reservation-vendor")
    public String getReservationsByVendorId(
            @RequestParam Long vendorId
    ) {
        List<Reservation> reservations = reservationService.findAll();
        List<Reservation> interested = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (shopService.findShopById(reservation.getShopId()) != null && shopService.findShopById(reservation.getShopId()).getVendor().getId().equals(vendorId)) {
                interested.add(reservation);
            }
        }
        return JSONConvert.JSONConverter(interested);
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.CREATED)
    public String createReservation(
            @RequestParam Long customerId,
            @RequestParam Long shopId,
            @Valid @RequestBody Reservation reservation,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            reservation.setCustomerId(customerId);
            reservation.setShopId(shopId);
            return JSONConvert.JSONConverter(reservationService.save(reservation));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<?> deleteReservation(
            @RequestParam Long customerId,
            @RequestParam Long reservationId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {

        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            Reservation reservation = reservationService.findReservationById(reservationId);
            if (reservation.getCustomerId().equals(customerId)) {
                reservationService.delete(reservation);
                return ResponseEntity.ok().build();
            }
        }
        throw new IllegalArgumentException();
    }
}
