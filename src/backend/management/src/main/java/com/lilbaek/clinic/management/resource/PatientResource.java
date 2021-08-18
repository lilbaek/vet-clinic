package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.db.ClientDbEntry;
import com.lilbaek.clinic.management.db.PatientDbEntry;
import com.lilbaek.clinic.management.resource.model.CreatePatientModel;
import com.lilbaek.clinic.management.resource.model.UpdatePatientModel;
import com.lilbaek.clinic.management.service.ClientService;
import com.lilbaek.clinic.management.service.PatientService;
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
	private final PatientService patientService;
	private final ClientService clientService;

	public PatientResource(PatientService patientService, ClientService clientService) {
		this.patientService = patientService;
		this.clientService = clientService;
	}

	@GetMapping(value = "/client/{id}")
	public Iterable<PatientDbEntry> getAll(@PathVariable Integer id) {
		return patientService.findByClientId(id);
	}

	@GetMapping(value = "/{id}")
	public PatientDbEntry findById(@PathVariable Integer id) {
		Optional<PatientDbEntry> result = patientService.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public PatientDbEntry save(@RequestBody @Valid CreatePatientModel entry) {
		Optional<ClientDbEntry> client = clientService.findById(entry.getClientId());
		if(client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client does not exist");
		}
		return patientService.save(PatientDbEntry.builder()
				.client(client.get())
				.name(entry.getName())
				.sex(entry.getSex())
				.build());
	}

	@PutMapping(value = "/{id}")
	public PatientDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdatePatientModel entry) {
		var record = patientService.findById(id);
		if (record.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		Optional<ClientDbEntry> client = clientService.findById(entry.getClientId());
		if(client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client does not exist");
		}
		var existing = record.get();
		existing.setName(entry.getName());
		existing.setSex(entry.getSex());
		existing.setClient(client.get());
		return patientService.save(existing);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		patientService.deleteById(id);
	}
}
