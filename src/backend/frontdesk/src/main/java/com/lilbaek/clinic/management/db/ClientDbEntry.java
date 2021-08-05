package com.lilbaek.clinic.management.db;

import com.lilbaek.shared.model.BaseDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDbEntry extends BaseDbEntity<Integer> {

    @Column("FullName")
    private String fullName;

    @Column("PreferredName")
    private String preferredName;

    @Column("Salutation")
    private String salutation;

    @Column("EmailAddress")
    private String emailAddress;

    @Column("PreferredDoctorId")
    private int preferredDoctorId;
}
