package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.PersonCreateDTO;
import com.dolph.DolphBank.dto.PersonDTO;
import com.dolph.DolphBank.dto.PersonUpdateDTO;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.exceptions.ForbiddenException;
import com.dolph.DolphBank.exceptions.NotFoundException;
import com.dolph.DolphBank.exceptions.ValidationException;
import com.dolph.DolphBank.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Value("${password.activate.endpoint}")
    private String passwordActivateEndpoint;

    private ModelMapper mapper;

    private final Pattern emailPattern = Pattern.compile("^[a-z0-9_.-]+@(.+)$");

    private final Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$");


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

    @Override
    public PersonDTO createPerson(PersonCreateDTO personCreateDTO) {
        if(!emailPattern.matcher(personCreateDTO.getEmail()).matches()) {
            throw new ValidationException("Invalid email format.");
        }

        String secretKey = RandomStringUtils.randomNumeric(6);

        Person person = Person.builder()
                .email(personCreateDTO.getEmail())
                .username(personCreateDTO.getUsername())
                .roles(personCreateDTO.getRoles())
                .active(true)
                .secretKey(secretKey)
                .build();

        personRepository.saveAndFlush(person);

        String text = "Secret key: " + secretKey + "\n" + "Link: " + passwordActivateEndpoint + "/" + person.getId();
        emailService.sendEmail(person.getEmail(), "Activate account", text);

        return mapper.map(person, PersonDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Person not found for this email :: " + email));

        if(!person.isActive()){
            throw new ForbiddenException("Person is not active.");
        }

        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), person.getAuthorities());
    }
}
