package com.lerprevencion.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonAsDTO {

    private Integer id;
    private String name;
    private String username;
    private Byte age;
    private String id_number;
    private String civil_status;
    private LocalDate birthdate;

}
