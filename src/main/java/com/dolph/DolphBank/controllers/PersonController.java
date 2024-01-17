package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.PersonCreateDTO;
import com.dolph.DolphBank.dto.PersonUpdateDTO;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        return new ResponseEntity<>("Person saved.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable("id") Long id, @RequestBody PersonUpdateDTO person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>("Person deleted.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody PersonCreateDTO personCreateDTO) {
        return ResponseEntity.ok(personService.createPerson(personCreateDTO));
    }


}
