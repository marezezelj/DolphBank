package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.PersonUpdateDTO;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.exceptions.NotFoundException;
import com.dolph.DolphBank.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public ResponseEntity<Object> updatePerson(Long id, PersonUpdateDTO personUpdateDTO) {
        Person tempPerson = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for this id :: " + id));

        if(personUpdateDTO.getFirstName() != null || !personUpdateDTO.getFirstName().isBlank()) tempPerson.setName(personUpdateDTO.getFirstName());
        if(personUpdateDTO.getLastName() != null || !personUpdateDTO.getLastName().isBlank()) tempPerson.setSurname(personUpdateDTO.getLastName());
        if(personUpdateDTO.getUsername() != null || !personUpdateDTO.getUsername().isBlank()) tempPerson.setUsername(personUpdateDTO.getUsername());
        if(personUpdateDTO.getEmail() != null || !personUpdateDTO.getEmail().isBlank()) tempPerson.setEmail(personUpdateDTO.getEmail());
        if(personUpdateDTO.getPhoneNumber() != null || !personUpdateDTO.getPhoneNumber().isBlank()) tempPerson.setPhone(personUpdateDTO.getPhoneNumber());
        if(personUpdateDTO.getAddress() != null || !personUpdateDTO.getAddress().isBlank()) tempPerson.setAddress(personUpdateDTO.getAddress());

        return ResponseEntity.ok().body("Person updated.");
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for this id :: " + id));
        person.setActive(false);
    }
}
