package com.lilbaek.clinic.management.db;

import com.lilbaek.shared.interfaces.IAggregateRoot;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDbEntry implements IAggregateRoot {

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

    @OneToMany(mappedBy="client", cascade = CascadeType.REMOVE)
    private Set<PatientDbEntry> patients;
}
