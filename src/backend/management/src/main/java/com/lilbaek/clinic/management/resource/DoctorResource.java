package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.domain.DoctorDbEntry;
import com.lilbaek.clinic.management.repository.DoctorRepository;
import com.lilbaek.clinic.management.resource.model.CreateDoctorModel;
import com.lilbaek.clinic.management.resource.model.UpdateDoctorModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/doctors")
@Validated
public class DoctorResource {

	private final DoctorRepository repository;

	public DoctorResource(DoctorRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Iterable<DoctorDbEntry> getAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}")
	public DoctorDbEntry findById(@PathVariable Integer id) {
		Optional<DoctorDbEntry> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public DoctorDbEntry save(@RequestBody @Valid CreateDoctorModel entry) {
		return repository.save(DoctorDbEntry.builder()
				.name(entry.getName())
				.build());
	}

	@PutMapping(value = "/{id}")
	public DoctorDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdateDoctorModel entry) {
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
