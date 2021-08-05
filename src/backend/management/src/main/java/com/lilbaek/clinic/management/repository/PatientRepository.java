package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.domain.PatientDbEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<PatientDbEntry, Integer> {
    List<PatientDbEntry> findByClientId(Integer clientId);
}
