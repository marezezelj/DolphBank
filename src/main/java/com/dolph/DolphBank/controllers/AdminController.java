package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final PersonService personService;

    @PostMapping("/hire/")
    public ResponseEntity<Object> hireEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
        personService.hireEmployee(employeeCreateDTO);

        return new ResponseEntity<>("Employee hired.", HttpStatus.OK);
    }
}
