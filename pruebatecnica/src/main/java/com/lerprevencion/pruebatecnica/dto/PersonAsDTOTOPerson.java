package com.lerprevencion.pruebatecnica.dto;

import com.lerprevencion.pruebatecnica.entity.CivilStatusEntity;
import com.lerprevencion.pruebatecnica.entity.ECivilStatus;
import com.lerprevencion.pruebatecnica.entity.PersonEntity;
import com.lerprevencion.pruebatecnica.mapper.IMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonAsDTOTOPerson implements IMapper<PersonAsDTO, PersonEntity> {
    @Override
    public PersonEntity map(PersonAsDTO personAsDTO) {

        // Capitalizamos texto civil_status desde request de la forma "palabra" : "Palabra"
        personAsDTO.setCivil_status(personAsDTO.getCivil_status().substring(0, 1).toUpperCase() +
                personAsDTO.getCivil_status().substring(1).toLowerCase());

        // conversión String texto civil_status desde request a CivilStatusEntity
        CivilStatusEntity civil_status_save = null;
        try {
            ECivilStatus e_civil_status = ECivilStatus.valueOf(personAsDTO.getCivil_status());
            civil_status_save = CivilStatusEntity.builder()
                    .civil_status(e_civil_status)
                    .build();
        } catch (Exception e) {
            System.out.println(
                    ("The string does not correspond to any value in the ECivilStatus enumeration \n" +
                            "or there was an error converting to CivilStatusEntity. - Error: ")
                            .concat(e.getMessage()));
        }

        return PersonEntity.builder()
                .id(personAsDTO.getId())
                .name(personAsDTO.getName())
                .username(personAsDTO.getUsername())
                .age(personAsDTO.getAge())
                .id_number(personAsDTO.getId_number())
                .civil_status(civil_status_save)
                .birthdate(personAsDTO.getBirthdate())
                .build();
    }

}
