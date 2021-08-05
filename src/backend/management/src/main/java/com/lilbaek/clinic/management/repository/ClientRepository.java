package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.domain.ClientDbEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientDbEntry, Integer> {
    Optional<ClientDbEntry> findByEmailAddressIgnoreCase(String email);
}
