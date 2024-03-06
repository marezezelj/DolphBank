package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.*;
import com.dolph.DolphBank.entites.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAllPersons();
    void savePerson(Person person);

    ResponseEntity<Object> updatePerson(Long id, PersonUpdateDTO person);

    void deletePerson(Long id);

    PersonDTO createPerson(PersonCreateDTO personCreateDTO);

    void activatePassword(PasswordDTO passwordDTO, Long id);

    Person findPersonByEmail(String email);

    void hireEmployee(EmployeeCreateDTO employeeCreateDTO);
}
