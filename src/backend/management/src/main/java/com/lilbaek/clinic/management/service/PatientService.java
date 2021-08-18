package com.lilbaek.clinic.management.service;

import com.lilbaek.clinic.management.db.PatientDbEntry;
import com.lilbaek.clinic.management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository clientRepository) {
        this.patientRepository = clientRepository;
    }

    public Optional<PatientDbEntry> findById(int id) {
        return patientRepository.findById(id);
    }

    public Iterable<PatientDbEntry> findByClientId(Integer id) {
        return patientRepository.findByClientId(id);
    }

    public PatientDbEntry save(PatientDbEntry entry) {
        return patientRepository.save(entry);
    }

    public void deleteById(Integer id) {
        patientRepository.deleteById(id);
    }
}
