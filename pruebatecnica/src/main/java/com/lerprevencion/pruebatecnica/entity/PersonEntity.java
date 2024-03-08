package com.lerprevencion.pruebatecnica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table (name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "person_id")
    private Integer id;

    @NotBlank
    @Size (min = 2, max = 60)
    private String name;

    @NotBlank
    @Size (min = 3, max = 60)
    private String username;

    @NotNull
    @Min(12)
    @Max(80)
    @Column (name = "age_person")
    private byte age;

    @NotBlank
    @Size(min=8, max=11)
    private String id_number;

    @ManyToOne (fetch = FetchType.EAGER, targetEntity = CivilStatusEntity.class, cascade = CascadeType.ALL)
    @JoinTable (name = "persons_civil_status",
            joinColumns = @JoinColumn (name = "person_id", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "civil_status_id", nullable = false))
    private CivilStatusEntity civil_status;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

}
