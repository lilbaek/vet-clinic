package com.lilbaek.clinic.management.service;

import com.lilbaek.clinic.management.db.RoomDbEntry;
import com.lilbaek.clinic.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Iterable<RoomDbEntry> findAll() {
        return roomRepository.findAll();
    }

    public Optional<RoomDbEntry> findById(Integer id) {
        return roomRepository.findById(id);
    }

    public RoomDbEntry save(RoomDbEntry save) {
        return roomRepository.save(save);
    }

    public void deleteById(Integer id) {
        //TODO: Validate that it can be deleted
        roomRepository.deleteById(id);
    }
}
