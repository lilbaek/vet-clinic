package com.lilbaek.clinic.management.repository;

import com.lilbaek.clinic.management.db.ClientDbEntry;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends R2dbcRepository<ClientDbEntry, Integer> {
}
