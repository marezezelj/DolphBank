package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.services.EmployeeService;
import com.dolph.DolphBank.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final PersonService personService;

    private final EmployeeService employeeService;

    @PostMapping("/hire/")
    public ResponseEntity<Object> hireEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
        personService.hireEmployee(employeeCreateDTO);

        return new ResponseEntity<>("Employee hired.", HttpStatus.OK);
    }

    @PutMapping("/update_employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        employeeService.updateEmployee(id, employeeCreateDTO);

        return new ResponseEntity<>("Employee updated.", HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    //TODO implementirati transfer klijenata na drugog zaposlenog
    @PutMapping("/fire_employee/{id}")
    public ResponseEntity<Object> fireEmployee(@PathVariable Long id){
        employeeService.relocateClients(id);
        employeeService.fireEmployee(id);
        return new ResponseEntity<>("Employee successfully fired.", HttpStatus.OK);
    }

}
