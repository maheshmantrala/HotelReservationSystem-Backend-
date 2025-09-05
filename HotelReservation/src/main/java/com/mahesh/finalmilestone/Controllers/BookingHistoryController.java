package com.mahesh.finalmilestone.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.finalmilestone.Entity.BookingHistory;
import com.mahesh.finalmilestone.Services.ReservationService;

@RestController
@RequestMapping("/api/booking-history")
public class BookingHistoryController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingHistory>> getBookingHistoryByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(reservationService.getBookingHistoryByUser(userId), HttpStatus.OK);
    }

  
}