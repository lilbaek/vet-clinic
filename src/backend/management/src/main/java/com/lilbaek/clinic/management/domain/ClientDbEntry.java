package com.lilbaek.clinic.management.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Clients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class ClientDbEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "PreferredName")
    private String preferredName;

    @Column(name = "Salutation")
    private String salutation;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "PreferredDoctorId")
    private int preferredDoctorId;
}
