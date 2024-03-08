package com.lerprevencion.pruebatecnica.repository;

import com.lerprevencion.pruebatecnica.entity.ECivilStatus;
import com.lerprevencion.pruebatecnica.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("SELECT p FROM PersonEntity p WHERE p.name LIKE CONCAT(:startsWith, '%')")
    List<PersonEntity> filterByNamesStartingWith(@Param("startsWith") String startsWith);

    @Query("SELECT p FROM PersonEntity p WHERE p.username LIKE CONCAT(:startsWith, '%')")
    List<PersonEntity> filterByUsernameStartingWith(@Param("startsWith") String startsWith);

    @Query("SELECT p FROM PersonEntity p WHERE p.age = :age")
    List<PersonEntity> filterByAge(@Param("age") byte age);

    @Query("SELECT p FROM PersonEntity p WHERE p.id_number = :id_number")
    List<PersonEntity> filterByIdNumber(@Param("id_number") String id_number);

    @Query("SELECT p FROM PersonEntity p WHERE p.civil_status.civil_status = :civilStatus")
    List<PersonEntity> filterByCivilStatus(@Param("civilStatus") ECivilStatus civilStatus);


    // nombres ascendente-descendente
    @Query("SELECT p FROM PersonEntity p ORDER BY p.name ASC")
    List<PersonEntity> sortAllOrderedByNameAsc();

    @Query("SELECT p FROM PersonEntity p ORDER BY p.name DESC")
    List<PersonEntity> sortAllOrderedByNameDesc();

    // apellidos ascendente-descendente
    @Query("SELECT p FROM PersonEntity p ORDER BY p.username ASC")
    List<PersonEntity> sortAllOrderedByUsernameAsc();

    @Query("SELECT p FROM PersonEntity p ORDER BY p.username DESC")
    List<PersonEntity> sortAllOrderedByUsernameDesc();

    // edades ascendente-descendente
    @Query("SELECT p FROM PersonEntity p ORDER BY p.age ASC")
    List<PersonEntity> sortAllOrderedByAgeAsc();

    @Query("SELECT p FROM PersonEntity p ORDER BY p.age DESC")
    List<PersonEntity> sortAllOrderedByAgeDesc();

    // estado civil ascendente-descendente
    @Query("SELECT p FROM PersonEntity p ORDER BY p.civil_status.civil_status ASC")
    List<PersonEntity> sortAllOrderedByCivilStatusAsc();

    @Query("SELECT p FROM PersonEntity p ORDER BY p.civil_status.civil_status DESC")
    List<PersonEntity> sortAllOrderedByCivilStatusDesc();

    // fecha nacimiento registro ascendente-descendente
    @Query("SELECT p FROM PersonEntity p ORDER BY p.birthdate ASC")
    List<PersonEntity> sortAllOrderedByBirthDateAsc();

    @Query("SELECT p FROM PersonEntity p ORDER BY p.birthdate DESC")
    List<PersonEntity> sortAllOrderedByBirthDateDesc();


}
