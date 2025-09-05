package com.mahesh.finalmilestone.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.finalmilestone.Entity.Reservation;
import com.mahesh.finalmilestone.Services.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.bookRoom(reservation), HttpStatus.CREATED);
    }
    
    
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
    	return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getReservationById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
        return new ResponseEntity<>(reservationService.updateReservation(id, reservationDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkRoomAvailability(
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam("roomType") String roomType) {

        boolean isAvailable = reservationService.isRoomAvailable(roomType, startDate, endDate);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(reservationService.getReservationsByUser(userId), HttpStatus.OK);
    }
}