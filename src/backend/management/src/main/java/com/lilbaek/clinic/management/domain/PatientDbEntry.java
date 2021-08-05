package com.lilbaek.clinic.management.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Patients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PatientDbEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "ClientId")
    private Integer clientId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Sex")
    private String sex;
}
