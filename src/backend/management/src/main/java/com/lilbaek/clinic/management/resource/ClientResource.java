package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.db.ClientDbEntry;
import com.lilbaek.clinic.management.resource.model.CreateClientModel;
import com.lilbaek.clinic.management.resource.model.UpdateClientModel;
import com.lilbaek.clinic.management.service.ClientService;
import com.lilbaek.clinic.management.service.DoctorService;
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
	private final ClientService clientService;
	private final DoctorService doctorService;

	public ClientResource(ClientService clientService, DoctorService doctorService) {
		this.clientService = clientService;
		this.doctorService = doctorService;
	}

	@GetMapping
	public Iterable<ClientDbEntry> getAll() {
		return clientService.findAll();
	}

	@GetMapping(value = "/{id}")
	public ClientDbEntry findById(@PathVariable Integer id) {
		Optional<ClientDbEntry> result = clientService.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public ClientDbEntry save(@RequestBody @Valid CreateClientModel entry) {
		var existing = clientService.findByEmailAddressIgnoreCase(entry.getEmailAddress());
		if (existing.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
		}
		var doctor = doctorService.findById(entry.getPreferredDoctorId());
		if (doctor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor does not exist");
		}
		return clientService.save(ClientDbEntry.builder()
				.fullName(entry.getFullName())
				.preferredName(entry.getPreferredName())
				.salutation(entry.getSalutation())
				.emailAddress(entry.getEmailAddress())
				.preferredDoctorId(entry.getPreferredDoctorId())
				.build());
	}

	@PutMapping(value = "/{id}")
	public ClientDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdateClientModel entry) {
		var record = clientService.findById(id);
		if (record.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		var doctor = doctorService.findById(entry.getPreferredDoctorId());
		if (doctor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor does not exist");
		}
		var existing = record.get();
		existing.setPreferredName(entry.getPreferredName());
		existing.setFullName(entry.getFullName());
		existing.setEmailAddress(entry.getEmailAddress());
		existing.setPreferredDoctorId(entry.getPreferredDoctorId());
		existing.setSalutation(entry.getSalutation());
		clientService.save(existing);
		return existing;
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.deleteById(id);
	}
}
