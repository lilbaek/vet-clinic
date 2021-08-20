package com.lilbaek.clinic.management.repository.db;

import com.lilbaek.shared.interfaces.IAggregateRoot;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rooms")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDbEntry implements IAggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name")
    @NotNull
    @NotEmpty
    private String name;
}
