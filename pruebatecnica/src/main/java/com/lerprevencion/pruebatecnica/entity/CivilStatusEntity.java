package com.lerprevencion.pruebatecnica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table (name = "civil_status")
public class CivilStatusEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer civil_status_id;

    @Enumerated (EnumType.STRING)
    private ECivilStatus civil_status;

}
