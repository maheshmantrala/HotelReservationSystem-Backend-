package com.mahesh.finalmilestone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mahesh.finalmilestone.Entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.roomType = :roomType AND " +
           "((r.checkInDate <= :endDate AND r.checkOutDate >= :startDate))")
    List<Reservation> findByRoomTypeAndDates(@Param("roomType") String roomType,
                                              @Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

    List<Reservation> findByUserId(Long userId);
}