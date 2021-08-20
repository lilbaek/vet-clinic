package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.repository.db.PatientDbEntry;
import com.lilbaek.shared.interfaces.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends IRepository<PatientDbEntry, Integer> {
    List<PatientDbEntry> findByClientId(Integer clientId);
}
