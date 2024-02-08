package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.*;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.services.PersonService;
import com.dolph.DolphBank.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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

    // login and register methods

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody PersonCreateDTO personCreateDTO) {
        return ResponseEntity.ok(personService.createPerson(personCreateDTO));
    }

    @PostMapping("/activate_password/{id}")
    public ResponseEntity<Object> activatePassword(@Valid @RequestBody PasswordDTO passwordDTO, @PathVariable Long id) {
        personService.activatePassword(passwordDTO, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(personService.findPersonByEmail(loginRequest.getEmail()))));
    }

}
