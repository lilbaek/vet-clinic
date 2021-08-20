package com.lilbaek.clinic.management.resource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
