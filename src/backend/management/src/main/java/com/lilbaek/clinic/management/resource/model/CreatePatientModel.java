package com.lilbaek.clinic.management.resource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientModel {
	@NotEmpty
	private Integer clientId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String sex;
}
