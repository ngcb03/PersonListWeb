package com.lerprevencion.pruebatecnica.service;

import com.lerprevencion.pruebatecnica.dto.PersonAsDTO;
import com.lerprevencion.pruebatecnica.entity.ECivilStatus;

import java.util.List;

public interface IPersonService {

    public List<PersonAsDTO> findAll();
    public PersonAsDTO findById(Integer person_id);
    public List<PersonAsDTO> filterByName(String value);
    public List<PersonAsDTO> filterByUsername(String value);
    public List<PersonAsDTO> filterByAge(Byte age);
    public List<PersonAsDTO> filterByIdNumber(String value);
    public List<PersonAsDTO> filterByCivilStatus(ECivilStatus value);
    public List<PersonAsDTO> sortName(String sort_type);
    public List<PersonAsDTO> sortUsername(String sort_type);
    public List<PersonAsDTO> sortAge(String sort_type);
    public List<PersonAsDTO> sortIdNumber(String sort_type);
    public List<PersonAsDTO> sortCivilStatus(String sort_type);
    public List<PersonAsDTO> sortBirthDate(String sort_type);
    public boolean create(PersonAsDTO personAsDTO);
    public boolean update(Integer person_id, PersonAsDTO personAsDTO);
    public boolean delete(Integer person_id);

}
