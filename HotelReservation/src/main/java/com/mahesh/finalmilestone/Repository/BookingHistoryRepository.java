package com.mahesh.finalmilestone.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mahesh.finalmilestone.Entity.BookingHistory;

import java.util.List;

public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    List<BookingHistory> findByUserId(Long userId);
    List<BookingHistory> findByRoomType(String roomType);
    
}