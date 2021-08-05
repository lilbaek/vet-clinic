package com.lilbaek.clinic.management.control;

import com.lilbaek.clinic.management.db.ClientDbEntry;
import com.lilbaek.clinic.management.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public Mono<ClientDbEntry> findById(@PathVariable Integer id) {
        return repository.findById(id);
    }
}
