package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.PersonUpdateDTO;
import com.dolph.DolphBank.entites.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    void savePerson(Person person);

    ResponseEntity<Object> updatePerson(Long id, PersonUpdateDTO person);

    void deletePerson(Long id);
}