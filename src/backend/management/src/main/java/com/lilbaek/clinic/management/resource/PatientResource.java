package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.domain.PatientDbEntry;
import com.lilbaek.clinic.management.repository.ClientRepository;
import com.lilbaek.clinic.management.repository.PatientRepository;
import com.lilbaek.clinic.management.resource.model.CreatePatientModel;
import com.lilbaek.clinic.management.resource.model.UpdatePatientModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/patients")
@Validated
public class PatientResource {
	private final PatientRepository repository;
	private final ClientRepository clientRepository;

	public PatientResource(PatientRepository repository, ClientRepository clientRepository) {
		this.repository = repository;
		this.clientRepository = clientRepository;
	}

	@GetMapping(value = "/client/{id}")
	public Iterable<PatientDbEntry> getAll(@PathVariable Integer id) {
		return repository.findByClientId(id);
	}

	@GetMapping(value = "/{id}")
	public PatientDbEntry findById(@PathVariable Integer id) {
		Optional<PatientDbEntry> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public PatientDbEntry save(@RequestBody @Valid CreatePatientModel entry) {
		if(clientRepository.findById(entry.getClientId()).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client does not exist");
		}
		return repository.save(PatientDbEntry.builder()
				.clientId(entry.getClientId())
				.name(entry.getName())
				.sex(entry.getSex())
				.build());
	}

	@PutMapping(value = "/{id}")
	public PatientDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdatePatientModel entry) {
		var record = repository.findById(id);
		if (record.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		if(clientRepository.findById(entry.getClientId()).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client does not exist");
		}
		var existing = record.get();
		existing.setName(entry.getName());
		existing.setSex(entry.getSex());
		existing.setClientId(entry.getClientId());
		return repository.save(existing);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
