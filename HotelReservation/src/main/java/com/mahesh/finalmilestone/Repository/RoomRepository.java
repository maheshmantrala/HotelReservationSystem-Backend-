package com.mahesh.finalmilestone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahesh.finalmilestone.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}