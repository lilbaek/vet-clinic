package com.lilbaek.clinic.management.resource.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateClientModel {
	@NotEmpty
	private String fullName;

	@NotEmpty
	private String preferredName;

	@NotEmpty
	private String salutation;

	@NotEmpty
	private String emailAddress;

	@NotNull
	private int preferredDoctorId;
}
