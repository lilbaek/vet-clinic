package com.lilbaek.clinic.management.resource.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdatePatientModel {
	@NotEmpty
	private Integer clientId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String sex;
}
