package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.domain.RoomDbEntry;
import com.lilbaek.clinic.management.repository.RoomRepository;
import com.lilbaek.clinic.management.resource.model.CreateRoomModel;
import com.lilbaek.clinic.management.resource.model.UpdateRoomModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rooms")
@Validated
public class RoomResource {
    private final RoomRepository repository;

    public RoomResource(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<RoomDbEntry> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public RoomDbEntry findById(@PathVariable Integer id) {
        Optional<RoomDbEntry> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    @PostMapping
    public RoomDbEntry save(@RequestBody @Valid CreateRoomModel entry) {
        return repository.save(RoomDbEntry.builder().name(entry.getName()).build());
    }

    @PutMapping(value = "/{id}")
    public RoomDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdateRoomModel entry) {
        var record = repository.findById(id);
        if (record.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var existing = record.get();
        existing.setName(entry.getName());
        return repository.save(existing);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        //TODO: Validate that it can be deleted
        repository.deleteById(id);
    }
}
