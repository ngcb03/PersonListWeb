package com.lerprevencion.pruebatecnica.controller;

import com.lerprevencion.pruebatecnica.dto.PersonAsDTO;
import com.lerprevencion.pruebatecnica.entity.ECivilStatus;
import com.lerprevencion.pruebatecnica.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("technicaltest/v1/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;
    private static Integer persons_list_size = 0;

    @GetMapping("/")
    @ResponseStatus (HttpStatus.OK)
    public List<PersonAsDTO> findAll(){
        List<PersonAsDTO> personAsDTOList = this.personService.findAll();
        persons_list_size = personAsDTOList.size();
        return personAsDTOList;
    }

    @GetMapping("/{person_id}")
    @ResponseStatus (HttpStatus.OK)
    public PersonAsDTO findById(@PathVariable("person_id") Integer person_id){
        if(this.findAll().size() >= person_id){
            return this.personService.findById(person_id);
        } return null;
    }

    @GetMapping("/filter")
    @ResponseStatus (HttpStatus.OK)
    public List<PersonAsDTO> findByFilter(
            @RequestParam(name = "filter") String filter,
            @RequestParam(name = "value") String value ) {
        if (filter.equals("name")) {
            return this.personService.filterByName(value);
        } else if (filter.equals("username")) {
            return this.personService.filterByUsername(value);
        } else if (filter.equals("age")) {
            return this.personService.filterByAge(Byte.parseByte(value));
        } else if(filter.equals("id_number")) {
            return this.personService.filterByIdNumber(value);
        } else if (filter.equals("civil_status")) {
            ECivilStatus civil_status = ECivilStatus.valueOf(value);
            return this.personService.filterByCivilStatus(civil_status);
        } else { return null; }
    }

    @GetMapping("/sort")
    @ResponseStatus (HttpStatus.OK)
    public List<PersonAsDTO> findByOrder(
            @RequestParam(name = "filter") String filter,
            @RequestParam(name = "sort_type") String sort_type ){
        if(filter.equals("name")){
            return this.personService.sortName(sort_type);
        } else if(filter.equals("username")){
            return this.personService.sortUsername(sort_type);
        } else if(filter.equals("age")){
            return this.personService.sortAge(sort_type);
        } else if(filter.equals("id_number")) {
            return this.personService.sortIdNumber(sort_type);
        } else if(filter.equals("civil_status")){
            return this.personService.sortCivilStatus(sort_type);
        } else if(filter.equals("birth_date")){
            return this.personService.sortBirthDate(sort_type);
        } else { return null; }
    }

    @PostMapping("/create")
    @ResponseStatus (HttpStatus.CREATED)
    public void create(@RequestBody PersonAsDTO personAsDTO){
          this.personService.create(personAsDTO);
    }

    @PutMapping("/update/{person_id}")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void create(@PathVariable("person_id") Integer person_id,
            @RequestBody PersonAsDTO personAsDTO){
          this.personService.update(person_id, personAsDTO);
    }

    @DeleteMapping("/delete/{person_id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("person_id") Integer person_id){
          if(this.persons_list_size >= person_id){
              this.personService.delete(person_id);
          }
    }

}
