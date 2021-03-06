package com.lilbaek.clinic.management.repository.db;

import com.lilbaek.shared.interfaces.IAggregateRoot;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Patients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDbEntry implements IAggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name="ClientId", nullable=false)
    private ClientDbEntry client;

    @Column(name = "ClientId", insertable = false, updatable = false)
    private Integer clientId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Sex")
    private String sex;
}
