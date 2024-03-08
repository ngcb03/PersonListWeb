package com.lerprevencion.pruebatecnica.service;

import com.lerprevencion.pruebatecnica.dto.PersonAsDTO;
import com.lerprevencion.pruebatecnica.dto.PersonAsDTOTOPerson;
import com.lerprevencion.pruebatecnica.dto.PersonToPersonDTO;
import com.lerprevencion.pruebatecnica.entity.ECivilStatus;
import com.lerprevencion.pruebatecnica.entity.PersonEntity;
import com.lerprevencion.pruebatecnica.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    // convertir PersonEntity a PersonAsDTO
    @Autowired
    private PersonToPersonDTO convertToPersonAsDTO;

    // convertir PersonAsDTO a PersonEntity
    @Autowired
    private PersonAsDTOTOPerson convertToPerson;

    @Override
    public List<PersonAsDTO> findAll() {
        return this.convertToDTO(this.personRepository.findAll());
    }

    @Override
    public PersonAsDTO findById(Integer person_id) {
        PersonEntity personEntity = this.personRepository.findById(person_id).orElse(null);
        return this.convertToPersonAsDTO.map(personEntity);
    }

    @Override
    public List<PersonAsDTO> filterByName(String value) {
        return this.convertToDTO(this.personRepository.filterByNamesStartingWith(value));
    }

    @Override
    public List<PersonAsDTO> filterByUsername(String value) {
        return this.convertToDTO(this.personRepository.filterByUsernameStartingWith(value));
    }

    @Override
    public List<PersonAsDTO> filterByAge(Byte age) {
        return this.convertToDTO(this.personRepository.filterByAge(age));
    }

    @Override
    public List<PersonAsDTO> filterByIdNumber(String value) {
        return this.convertToDTO(this.personRepository.filterByIdNumber(value));
    }

    @Override
    public List<PersonAsDTO> filterByCivilStatus(ECivilStatus value) {
        return this.convertToDTO(this.personRepository.filterByCivilStatus(value));
    }

    @Override
    public List<PersonAsDTO> sortName(String sort_type) {
        if(sort_type.equals("asc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByNameAsc());
        } else if (sort_type.equals("desc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByNameDesc());
        } return null;
    }

    @Override
    public List<PersonAsDTO> sortUsername(String sort_type) {
        if(sort_type.equals("asc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByUsernameAsc());
        } else if (sort_type.equals("desc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByUsernameDesc());
        } return null;
    }

    @Override
    public List<PersonAsDTO> sortAge(String sort_type) {
        if(sort_type.equals("asc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByAgeAsc());
        } else if (sort_type.equals("desc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByAgeDesc());
        } return null;
    }

    @Override
    public List<PersonAsDTO> sortIdNumber(String sort_type) {
        List<PersonAsDTO> personAsDTOList = this.findAll();
        List<PersonAsDTO> personAsDTOListSend = new ArrayList<>();

        List<Integer> id_number_list = new ArrayList<>(); // Inicializa con ArrayList
        for (int i = 0; i < personAsDTOList.size(); i++) {
            id_number_list.add(Integer.parseInt(personAsDTOList.get(i).getId_number()));
        }
        try {
            if (sort_type.equals("asc")) {
                Collections.sort(id_number_list);
            } else if (sort_type.equals("desc")) {
                Collections.sort(id_number_list, Collections.reverseOrder());
            }
            for (int i = 0; i < id_number_list.size(); i++) {
                for (PersonAsDTO personAsDTO : personAsDTOList) {
                    if (Integer.parseInt(personAsDTO.getId_number()) == id_number_list.get(i)) {
                        personAsDTOListSend.add(personAsDTO);
                    }
                }
            }
        } catch (Exception e){
            System.out.println(("Error when trying to convert id_number from " +
                    "string to integer! - Error: ").concat(e.getMessage()));
        }
        return personAsDTOListSend;
    }

    @Override
    public List<PersonAsDTO> sortCivilStatus(String sort_type) {
        if(sort_type.equals("asc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByCivilStatusAsc());
        } else if (sort_type.equals("desc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByCivilStatusDesc());
        } return null;
    }

    @Override
    public List<PersonAsDTO> sortBirthDate(String sort_type){
        if(sort_type.equals("asc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByBirthDateAsc());
        } else if(sort_type.equals("desc")){
            return this.convertToDTO(this.personRepository.sortAllOrderedByBirthDateDesc());
        } return null;
    }

    private List<PersonAsDTO> convertToDTO(List<PersonEntity> personEntityList){
        List<PersonAsDTO> personAsDTOList = new ArrayList<>();
        for(PersonEntity personEntity: personEntityList){
            personAsDTOList.add(this.convertToPersonAsDTO.map(personEntity));
        } return personAsDTOList;
    }

    @Override
    public boolean create(PersonAsDTO personAsDTO) {
        try {
            PersonEntity personEntity = this.convertToPerson.map(personAsDTO);
            this.personRepository.save(personEntity);
            return true;
        } catch (Exception e){
            System.out.println("The Person not has been created. - Error: "
                    .concat(e.getMessage()));
        } return false;
    }

    @Override
    public boolean update(Integer person_id, PersonAsDTO personAsDTO) {
        try {
            PersonAsDTO personAsDTOAux = this.findById(person_id);
            personAsDTOAux.setName(personAsDTO.getName());
            personAsDTOAux.setUsername(personAsDTO.getUsername());
            personAsDTOAux.setAge(personAsDTO.getAge());
            personAsDTOAux.setCivil_status(personAsDTO.getCivil_status());

            PersonEntity personEntityDB = this.convertToPerson.map(personAsDTOAux);
            personEntityDB.setId(person_id);
            this.personRepository.save(personEntityDB);
            return true;
        } catch (Exception e){
            System.out.println("The Person not has been updated. - Error: "
                    .concat(e.getMessage()));
        } return false;
    }

    @Override
    public boolean delete(Integer person_id) {
        try {
            this.personRepository.deleteById(person_id);
            return true;
        } catch (Exception e){
            System.out.println("The Person not has been deleted. - Error: "
                    .concat(e.getMessage()));
        } return false;
    }
}
