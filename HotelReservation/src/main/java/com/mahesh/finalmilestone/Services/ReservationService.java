package com.mahesh.finalmilestone.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahesh.finalmilestone.Entity.BookingHistory;
import com.mahesh.finalmilestone.Entity.Reservation;
import com.mahesh.finalmilestone.Repository.BookingHistoryRepository;
import com.mahesh.finalmilestone.Repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;

    public Reservation bookRoom(Reservation reservation) {
        Reservation newReservation = reservationRepository.save(reservation);

        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setReservationId(newReservation.getId());
        bookingHistory.setUserId(newReservation.getUserId());
        bookingHistory.setRoomType(newReservation.getRoomType());
        bookingHistory.setCheckInDate(newReservation.getCheckInDate());
        bookingHistory.setCheckOutDate(newReservation.getCheckOutDate());
        bookingHistory.setStatus(newReservation.getStatus());
        bookingHistory.setTimestamp(LocalDateTime.now());

        bookingHistoryRepository.save(bookingHistory);

        return newReservation;
    }
    public List<Reservation> getAllReservations() {
    	return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Reservation is not found"));
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        reservation.setRoomType(reservationDetails.getRoomType());
        reservation.setCheckInDate(reservationDetails.getCheckInDate());
        reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
        reservation.setStatus(reservationDetails.getStatus());
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus("Cancelled");
        reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public boolean isRoomAvailable(String roomType, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findByRoomTypeAndDates(roomType, startDate, endDate);
        return reservations.isEmpty();
    }

    public List<BookingHistory> getBookingHistoryByUser(Long userId) {
        return bookingHistoryRepository.findByUserId(userId);
    }
}
