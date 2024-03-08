package com.lerprevencion.pruebatecnica.dto;

import com.lerprevencion.pruebatecnica.entity.PersonEntity;
import com.lerprevencion.pruebatecnica.mapper.IMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PersonToPersonDTO implements IMapper <PersonEntity, PersonAsDTO> {
    @Override
    public PersonAsDTO map(PersonEntity personEntity) {

        // conversión CivilStatusEntity a String para envío por response
        String civil_status_save = null;
        try{
            civil_status_save = personEntity.getCivil_status().getCivil_status().toString();
        } catch (Exception e){
            System.out.println(
                    "Error converting to String. - Error: "
                            .concat(e.getMessage()));
        }

        return PersonAsDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .username(personEntity.getUsername())
                .age(personEntity.getAge())
                .id_number(personEntity.getId_number())
                .civil_status(civil_status_save)
                .birthdate(personEntity.getBirthdate())
                .build();
    }
}
