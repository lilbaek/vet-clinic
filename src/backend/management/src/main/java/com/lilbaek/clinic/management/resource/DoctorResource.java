package com.lilbaek.clinic.management.resource;

import com.lilbaek.clinic.management.db.DoctorDbEntry;
import com.lilbaek.clinic.management.resource.model.CreateDoctorModel;
import com.lilbaek.clinic.management.resource.model.UpdateDoctorModel;
import com.lilbaek.clinic.management.service.DoctorService;
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

	private final DoctorService doctorService;

	public DoctorResource(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public Iterable<DoctorDbEntry> getAll() {
		return doctorService.findAll();
	}

	@GetMapping(value = "/{id}")
	public DoctorDbEntry findById(@PathVariable Integer id) {
		Optional<DoctorDbEntry> result = doctorService.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	@PostMapping
	public DoctorDbEntry save(@RequestBody @Valid CreateDoctorModel entry) {
		return doctorService.save(DoctorDbEntry.builder()
				.name(entry.getName())
				.build());
	}

	@PutMapping(value = "/{id}")
	public DoctorDbEntry update(@PathVariable Integer id, @RequestBody @Valid UpdateDoctorModel entry) {
		var record = doctorService.findById(id);
		if (record.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		var existing = record.get();
		existing.setName(entry.getName());
		return doctorService.save(existing);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		doctorService.deleteById(id);
	}
}
