package com.lilbaek.clinic.management.db;

import com.lilbaek.shared.interfaces.IAggregateRoot;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Doctors")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDbEntry implements IAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name")
    @NotNull
    @NotEmpty
    private String name;
}
