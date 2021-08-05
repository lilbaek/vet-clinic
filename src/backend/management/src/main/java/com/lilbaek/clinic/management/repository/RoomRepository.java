package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.domain.RoomDbEntry;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomDbEntry, Integer> {

}
