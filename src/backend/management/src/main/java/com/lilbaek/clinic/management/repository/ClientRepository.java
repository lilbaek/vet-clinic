package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.repository.db.ClientDbEntry;
import com.lilbaek.shared.interfaces.IRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends IRepository<ClientDbEntry, Integer> {
    Optional<ClientDbEntry> findByEmailAddressIgnoreCase(String email);
}
