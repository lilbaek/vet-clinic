package com.lilbaek.clinic.management.service;

import com.lilbaek.clinic.management.repository.db.DoctorDbEntry;
import com.lilbaek.clinic.management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository clientRepository) {
        this.doctorRepository = clientRepository;
    }

    public Optional<DoctorDbEntry> findById(int id) {
        return doctorRepository.findById(id);
    }

    public Iterable<DoctorDbEntry> findAll() {
        return doctorRepository.findAll();
    }

    public DoctorDbEntry save(DoctorDbEntry save) {
        return doctorRepository.save(save);
    }

    public void deleteById(Integer id) {
        //TODO: Validate that it can be deleted
        doctorRepository.deleteById(id);
    }
}
