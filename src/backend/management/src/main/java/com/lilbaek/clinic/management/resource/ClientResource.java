package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.domain.ClientDbEntry;
import com.lilbaek.clinic.management.repository.ClientRepository;
import com.lilbaek.clinic.management.repository.DoctorRepository;
import com.lilbaek.clinic.management.resource.model.CreateClientModel;
import com.lilbaek.clinic.management.resource.model.UpdateClientModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
@Validated
public class ClientResource {
	private final ClientRepository repository;
	private final DoctorRepository doctorRepository;

	public ClientResource(ClientRepository repository, DoctorRepository doctorRepository) {
		this.repository = repository;
		this.doctorRepository = doctorRepository;
	}

	@GetMapping
	public Iterable<ClientDbEntry> getAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ClientDbEntry findById(@PathVariable Integer id) {
		Optional<ClientDbEntry> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public ClientDbEntry save(@RequestBody @Valid CreateClientModel entry) {
		var existing = repository.findByEmailAddressIgnoreCase(entry.getEmailAddress());
		if (existing.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
		}
		var doctor = doctorRepository.findById(entry.getPreferredDoctorId());
		if (doctor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor does not exist");
		}
		return repository.save(ClientDbEntry.builder()
				.fullName(entry.getFullName())
				.preferredName(entry.getPreferredName())
				.salutation(entry.getSalutation())
				.emailAddress(entry.getEmailAddress())
				.preferredDoctorId(entry.getPreferredDoctorId())
				.build());
	}

	@PutMapping(value = "/{id}")
	public ClientDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdateClientModel entry) {
		var record = repository.findById(id);
		if (record.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		var doctor = doctorRepository.findById(entry.getPreferredDoctorId());
		if (doctor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor does not exist");
		}
		var existing = record.get();
		existing.setPreferredName(entry.getPreferredName());
		existing.setFullName(entry.getFullName());
		existing.setEmailAddress(entry.getEmailAddress());
		existing.setPreferredDoctorId(entry.getPreferredDoctorId());
		existing.setSalutation(entry.getSalutation());
		repository.save(existing);
		return existing;
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
