package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.domain.DoctorDbEntry;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorDbEntry, Integer> {

}
