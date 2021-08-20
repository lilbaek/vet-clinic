package com.lilbaek.clinic.management.service;

import com.lilbaek.clinic.management.repository.db.ClientDbEntry;
import com.lilbaek.clinic.management.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Iterable<ClientDbEntry> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientDbEntry> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public Optional<ClientDbEntry> findByEmailAddressIgnoreCase(String emailAddress) {
        return clientRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    public ClientDbEntry save(ClientDbEntry save) {
        return clientRepository.save(save);
    }

    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }
}
